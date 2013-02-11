/*
 * Copyright (c) 2012 The University of Manchester, UK.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the names of The University of Manchester nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package uk.org.taverna.server.client.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import uk.org.taverna.server.client.RunPermission;

import cs.man.ac.uk.tavernamobile.serverresource.xml.Credential;
import cs.man.ac.uk.tavernamobile.serverresource.xml.InputDescription;
import cs.man.ac.uk.tavernamobile.serverresource.xml.KeyPairCredential;
import cs.man.ac.uk.tavernamobile.serverresource.xml.MakeDirectory;
import cs.man.ac.uk.tavernamobile.serverresource.xml.PasswordCredential;
import cs.man.ac.uk.tavernamobile.serverresource.xml.PermissionDescription;
import cs.man.ac.uk.tavernamobile.serverresource.xml.Permission;
import cs.man.ac.uk.tavernamobile.serverresource.xml.TrustDescriptor;

public final class AndroidXMLWriter {

	static byte[] write(Object element) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Serializer serializer = new Persister(new CustomMatcher()); 
		
		try {
			serializer.write(element, os);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return os.toByteArray();
	}

	public static byte[] mkdir(String name) {
		MakeDirectory md = new MakeDirectory();
		md.setName(name);

		return write(md);
	}

	public static byte[] inputValue(String value) {
		InputDescription.Value idv = new InputDescription.Value();
		idv.setValue(value);

		InputDescription id = new InputDescription();
		id.setValue(idv);

		return write(id);
	}

	public static byte[] inputFile(File file) {
		InputDescription.File idf = new InputDescription.File();
		idf.setValue(file.getPath());

		InputDescription id = new InputDescription();
		id.setFile(idf);

		return write(id);
	}

	public static byte[] runPermission(String username, RunPermission permission) {
		PermissionDescription pd = new PermissionDescription();
		pd.setUserName(username);
		pd.setPermission(Permission.fromValue(permission.permission));

		return write(pd);
	}

	public static byte[] runServiceUserPassCredential(URI uri, String username,
			String password) {
		PasswordCredential pc = new PasswordCredential();
		pc.setServiceURI(uri);
		pc.setUsername(username);
		pc.setPassword(password);

		Credential cred = new Credential();
		cred.setUserpass(pc);

		return write(cred);
	}

	public static byte[] runServiceKeyPairCredential(URI uri, String filename,
			String type, String name, String password) {
		KeyPairCredential kpc = new KeyPairCredential();
		kpc.setServiceURI(uri);
		kpc.setCredentialFile(filename);
		kpc.setCredentialName(name);
		kpc.setFileType(type);
		kpc.setUnlockPassword(password);

		Credential cred = new Credential();
		cred.setKeypair(kpc);

		return write(cred);
	}

	public static byte[] runTrustedIdentity(String filename, String type) {
		TrustDescriptor td = new TrustDescriptor();
		td.setCertificateFile(filename);
		td.setFileType(type);

		return write(td);
	}
}
