package com.bz.encodings;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CheckEncoding {
	FileInputStream fileInputStream = null;
	InputStreamReader inputStreamReader = null;

	public CheckEncoding() {
		super();
	}

	public boolean isUtf8(String path) throws IOException {
		File file = new File(path);
		Charset charset = detectCharset(file, Charset.forName("UTF-8"));

		if (charset != null)
			return true;
		else
			return false;
	}

	private Charset detectCharset(File file, Charset charset) {
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

			CharsetDecoder decoder = charset.newDecoder();
			decoder.reset();

			byte[] buffer = new byte[512];
			boolean identified = false;
			while ((input.read(buffer) != -1) && (!identified)) {
				identified = identify(buffer, decoder);
			}

			input.close();

			if (identified) {
				return charset;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
	}

	private boolean identify(byte[] bytes, CharsetDecoder decoder) {
		try {
			decoder.decode(ByteBuffer.wrap(bytes));
		} catch (CharacterCodingException e) {
			return false;
		}
		return true;
	}

}
