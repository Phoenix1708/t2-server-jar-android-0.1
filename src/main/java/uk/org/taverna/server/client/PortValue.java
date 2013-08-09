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

package uk.org.taverna.server.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.AbstractList;
import java.util.List;

import org.apache.commons.lang.text.StrBuilder;

/**
 * 
 * @author Robert Haines
 */
public abstract class PortValue extends AbstractList<PortValue> {

	public static final String PORT_ERROR_TYPE = "application/x-error";
	public static final String PORT_LIST_TYPE = "application/x-list";

	protected final Run run;
	protected final URI reference;
	protected final String contentType;
	protected final long size;

	PortValue(Run run, URI reference, String type, long size) {
		this.run = run;
		this.reference = reference;
		this.contentType = type;
		this.size = size;
	}

	public static PortData newPortData(Run run, URI reference, String type,
			long size) {
		return new PortData(run, reference, type, size);
	}

	public static PortList newPortList(Run run, URI reference,
			List<PortValue> list) {
		return new PortList(run, reference, list);
	}

	public static PortError newPortError(Run run, URI reference, long size) {
		return new PortError(run, reference, size);
	}

	public String getContentType() {
		return contentType;
	}

	public abstract byte[] getData();

	public abstract byte[] getData(int index);

	public abstract InputStream getDataStream();

	public abstract void writeDataToFile(File file) throws IOException;

	public String getDataAsString() {
		return new String(getData());
	}

	public abstract long getDataSize();

	public URI getReference() {
		return reference;
	}

	public Run getRun() {
		return run;
	}

	public abstract boolean isError();

	@Override
	public String toString() {
		return toString(0);
	}

	public String toString(int indent) {
		StrBuilder message = new StrBuilder();

		message.appendPadding(indent, ' ');
		message.appendln(reference.toASCIIString());
		message.appendPadding(indent, ' ');
		message.appendln(contentType);
		message.appendPadding(indent, ' ');
		message.append(getDataSize());

		return message.toString();
	}
}
