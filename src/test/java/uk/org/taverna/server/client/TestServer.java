/*
 * Copyright (c) 2010-2012 The University of Manchester, UK.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.net.URI;

import org.junit.AfterClass;
import org.junit.Test;

public class TestServer extends TestBase {

	@Test
	public void testServer() {
		Server server = new Server(serverURI);
		assertNotNull("Server instance", server);

		Server other = new Server(serverURI);
		assertNotNull("Other Server instance", other);
		assertNotSame("Server objects should not be the same", other, server);

		URI uri = server.getURI();
		assertEquals(serverURI, uri);
	}

	@Test
	public void testServerRunCreation() {
		Server server = new Server(serverURI);
		byte[] workflow = loadWorkflow(WKF_PASS);

		server.createRun(workflow, user1);
	}

	@Test(expected = AccessForbiddenException.class)
	public void testServerLimits() {
		Server server = new Server(serverURI);
		int limit = server.getRunLimit(user1);
		byte[] workflow = loadWorkflow(WKF_PASS);

		// Add 1 here so we are sure to go over the limit!
		for (int i = 0; i < (limit + 1); i++) {
			server.createRun(workflow, user1);
		}
	}

	@AfterClass
	public static void deleteAll() {
		Server server = new Server(serverURI);
		server.deleteAllRuns(user1);
	}
}
