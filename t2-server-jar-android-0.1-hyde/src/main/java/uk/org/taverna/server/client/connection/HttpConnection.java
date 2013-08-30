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

package uk.org.taverna.server.client.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import org.apache.commons.lang.math.LongRange;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import local.org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import local.org.apache.http.util.EntityUtils;

import uk.org.taverna.server.client.NetworkConnectionException;
import uk.org.taverna.server.client.connection.params.ConnectionParams;

/**
 * 
 * @author Robert Haines (Modified by Hyde Zhang)
 */
public class HttpConnection extends AbstractConnection {

	protected final URI uri;

	protected final ConnectionParams params;

	protected HttpClient myhttpClient;
	protected final HttpContext httpContext;

	HttpConnection(URI uri, ConnectionParams params) {
		this.uri = uri;
		this.params = params;
		//myhttpClient = new DefaultHttpClient(new BasicHttpParams());
		httpContext = new BasicHttpContext();
	}

	@Override
	public URI create(URI uri, InputStream content, long length, MimeType type,
			UserCredentials credentials) throws NetworkConnectionException {
		HttpPost request = new HttpPost(uri);
		URI location = null;

		if (credentials != null) {
			credentials.authenticate(request, httpContext);
		}

		HttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
		HttpResponse response = null;
		try {
			InputStreamEntity entity = new InputStreamEntity(content, length);
			entity.setContentType(type.contentType);
			request.setEntity(entity);

			response = httpClient.execute(request, httpContext);

			if (!isSuccess(response, HttpURLConnection.HTTP_CREATED)) {
				error(response, uri);
			}

			location = URI
					.create(response.getHeaders("location")[0].getValue());
		} catch (ClientProtocolException e) {
				throw new NetworkConnectionException(e.getMessage(), e);
		} catch (IOException e) {
				throw new NetworkConnectionException(e.getMessage(), e);
		} finally {
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}

		return location;
	}

	@Override
	public InputStream readStream(URI uri, MimeType type, LongRange range,
			UserCredentials credentials) throws NetworkConnectionException {
		HttpEntity entity = null;
		try{
			entity = get(uri, type, range, credentials);
		}catch(NetworkConnectionException e){
			throw new NetworkConnectionException(e.getMessage(), e);
		}
		//HttpEntity entity = get(uri, type, range, credentials);
		
		InputStream stream = null;
		try {
			stream = entity.getContent();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stream;
	}

	private HttpEntity get(URI uri, MimeType type, LongRange range,
			UserCredentials credentials) throws NetworkConnectionException {
		HttpGet request = new HttpGet(uri);
		int success = HttpURLConnection.HTTP_OK;

		if (type != null) {
			request.addHeader("Accept", type.contentType);
		}

		if (range != null) {
			request.addHeader("Range", "bytes=" + range.getMinimumLong() + "-"
					+ range.getMaximumLong());
			success = HttpURLConnection.HTTP_PARTIAL;
		}

		if (credentials != null) {
			credentials.authenticate(request, httpContext);
		}

		myhttpClient = new DefaultHttpClient(new BasicHttpParams());
		HttpResponse response = null;
		try {
			//response = httpClient.execute(request, httpContext);
			response = myhttpClient.execute(request, httpContext);

			HttpEntity entity = response.getEntity();
			if (isSuccess(response, success)) {
				return entity;
			} else {
				error(response, entity, uri);
			}

		} catch (ClientProtocolException e) {
			throw new NetworkConnectionException(e.getMessage(), e);
		} catch (IOException e) {
			throw new NetworkConnectionException(e.getMessage(), e);
		} catch (Exception e){
			throw new NetworkConnectionException(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public byte[] read(URI uri, MimeType type, LongRange range,
			UserCredentials credentials) throws NetworkConnectionException {

		HttpEntity entity = get(uri, type, range, credentials);

		try {
			return EntityUtils.toByteArray(entity);
		} catch (IOException e) {
			throw new NetworkConnectionException(e.getMessage(), e);
		} finally {
			EntityUtils.consumeQuietly(entity);
			HttpClientUtils.closeQuietly(myhttpClient);
		}
	}

	@Override
	public URI update(URI uri, InputStream content, long length,
			MimeType type, UserCredentials credentials) throws NetworkConnectionException {
		HttpPut request = new HttpPut(uri);

		if (credentials != null) {
			credentials.authenticate(request, httpContext);
		}

		HttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
		HttpResponse response = null;
		try {
			InputStreamEntity entity = new InputStreamEntity(content, length);
			entity.setContentType(type.contentType);
			request.setEntity(entity);

			response = httpClient.execute(request, httpContext);

			/*
			 * There are three possible "success" responses from the server:
			 * 
			 * 1) 200 (OK) - returned when we have set a parameter.
			 * 
			 * 2) 201 (Created) - returned when we have uploaded new data.
			 * 
			 * 3) 204 (No Content) - returned when we have modified data.
			 */
			if (isSuccess(response, HttpURLConnection.HTTP_OK)) {
				return uri;
			} else if (isSuccess(response, HttpURLConnection.HTTP_CREATED)) {
				return URI
						.create(response.getHeaders("location")[0].getValue());
			} else if (isSuccess(response, HttpURLConnection.HTTP_NO_CONTENT)) {
				return uri;
			} else {
				error(response, uri);
			}
		} catch (ClientProtocolException e) {
			throw new NetworkConnectionException(e.getMessage(), e);
		} catch (IOException e) {
			throw new NetworkConnectionException(e.getMessage(), e);
		} finally {
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}

		return null;
	}

	@Override
	public boolean delete(URI uri, UserCredentials credentials) throws NetworkConnectionException {
		HttpDelete request = new HttpDelete(uri);

		if (credentials != null) {
			credentials.authenticate(request, httpContext);
		}

		HttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
		HttpResponse response = null;
		try {
			response = httpClient.execute(request, httpContext);

			if (isSuccess(response, HttpURLConnection.HTTP_NO_CONTENT)) {
				return true;
			} else {
				error(response, uri);
			}
		} catch (ClientProtocolException e) {
			throw new NetworkConnectionException(e.getMessage(), e);
		} catch (IOException e) {
			throw new NetworkConnectionException(e.getMessage(), e);
		} finally {
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}

		return false;
	}

	private boolean isSuccess(HttpResponse response, int success) {
		return response.getStatusLine().getStatusCode() == success;
	}

	private void error(HttpResponse response, HttpEntity entity, URI requestURI) {
		int status = response.getStatusLine().getStatusCode();

		// We need to save any content from the entity for error messages, then
		// reset it by consuming it.
		String content = null;
		if (entity != null) {
			try {
				content = EntityUtils.toString(entity);
			} catch (IOException e) {
				// Ignore.
			} finally {
				EntityUtils.consumeQuietly(entity);
			}
		}

		switch (status) {
		case HttpURLConnection.HTTP_NOT_FOUND:
			throw new AttributeNotFoundException(requestURI);
		case HttpURLConnection.HTTP_FORBIDDEN:
			throw new AccessForbiddenException(requestURI);
		case HttpURLConnection.HTTP_UNAUTHORIZED:
			throw new AuthorizationException(requestURI);
		case HttpURLConnection.HTTP_INTERNAL_ERROR:
			if (content == null || content.isEmpty()) {
				content = "<not specified>";
			}

			throw new InternalServerException(requestURI, content);
		default:
			String reason = response.getStatusLine().getReasonPhrase();

			throw new UnexpectedResponseException(requestURI, status, reason);
		}
	}

	private void error(HttpResponse response, URI requestURI) {
		error(response, response.getEntity(), requestURI);
	}
}
