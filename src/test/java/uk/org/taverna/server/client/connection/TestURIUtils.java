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

package uk.org.taverna.server.client.connection;

import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Test;

/**
 * 
 * @author Robert Haines
 * 
 */
public class TestURIUtils {

	// Starting blocks
	private final URI standardHTTP = URI.create("http://www.example.com");
	private final URI standardHTTPS = URI.create("https://www.example.com");
	private final URI userpassHTTPS = URI
			.create("https://user:pass@www.example.com");
	private final URI slashHTTP = URI.create("http://www.example.com/");
	private final URI pathHTTP = URI.create("http://www.example.com/path");
	private final URI pathSlashHTTP = URI
			.create("http://www.example.com/path/");

	// Results
	private final URI addedStandardHTTP = URI
			.create("http://www.example.com/path/added");
	private final URI longAddedStandardHTTP = URI
			.create("http://www.example.com/path/path/added");

	@Test
	public void testStripUserInfo() {
		URI stripped = URIUtils.stripUserInfo(userpassHTTPS);
		assertTrue(stripped.equals(standardHTTPS));
	}

	@Test
	public void testAddToPath() {
		URI added = URIUtils.addToPath(standardHTTP, "path/added");
		assertTrue(added.equals(addedStandardHTTP));

		added = URIUtils.addToPath(standardHTTP, "/path/added");
		assertTrue(added.equals(addedStandardHTTP));

		added = URIUtils.addToPath(slashHTTP, "path/added");
		assertTrue(added.equals(addedStandardHTTP));

		added = URIUtils.addToPath(slashHTTP, "/path/added");
		assertTrue(added.equals(addedStandardHTTP));

		added = URIUtils.addToPath(pathHTTP, "path/added");
		assertTrue(added.equals(longAddedStandardHTTP));

		added = URIUtils.addToPath(pathHTTP, "/path/added");
		assertTrue(added.equals(longAddedStandardHTTP));

		added = URIUtils.addToPath(pathSlashHTTP, "path/added");
		assertTrue(added.equals(longAddedStandardHTTP));

		added = URIUtils.addToPath(pathSlashHTTP, "/path/added");
		assertTrue(added.equals(longAddedStandardHTTP));
	}
}