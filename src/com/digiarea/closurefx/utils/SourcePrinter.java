package com.digiarea.closurefx.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class SourcePrinter {

	/** The level. */
	private int level = 0;

	/** The indented. */
	private boolean indented = false;

	/** The indent. */
	public static final String INDENT = "    ";

	public static final String NEWLINE = "\n";

	private byte[] indent;
	private byte[] newline;

	/**
	 * 
	 * US-ASCII Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block
	 * of the Unicode character set
	 * 
	 * ISO-8859-1 ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
	 * 
	 * UTF-8 Eight-bit UCS Transformation Format
	 * 
	 * UTF-16BE Sixteen-bit UCS Transformation Format, big-endian byte order
	 * 
	 * UTF-16LE Sixteen-bit UCS Transformation Format, little-endian byte order
	 * 
	 * UTF-16 Sixteen-bit UCS Transformation Format, byte order identified by an
	 * optional byte-order mark
	 */
	private Charset charset;

	/** The buffer. */
	private final OutputStream out;

	public SourcePrinter(OutputStream out, String encoding) {
		super();
		this.out = out;
		if (encoding != null && Charset.isSupported(encoding)) {
			this.charset = Charset.forName(encoding);
		} else {
			this.charset = Charset.forName("UTF-8");
		}
		indent = INDENT.getBytes(charset);
		newline = NEWLINE.getBytes(charset);
	}

	/**
	 * Indent.
	 */
	public void indent() {
		level++;
	}

	/**
	 * Unindent.
	 */
	public void unindent() {
		level--;
	}

	/**
	 * Make indent.
	 * 
	 * @throws IOException
	 */
	private void makeIndent() throws IOException {
		for (int i = 0; i < level; i++) {
			out.write(indent);
		}
	}

	/**
	 * Prints the.
	 * 
	 * @param arg
	 *            the arg
	 * @throws IOException
	 */
	public void print(String arg) throws IOException {
		if (!indented) {
			makeIndent();
			indented = true;
		}
		out.write(arg.getBytes(charset));
	}

	/**
	 * Prints the ln.
	 * 
	 * @param arg
	 *            the arg
	 * @throws IOException
	 */
	public void printLn(String arg) throws IOException {
		print(arg);
		printLn();
	}

	/**
	 * Prints the ln.
	 * 
	 * @throws IOException
	 */
	public void printLn() throws IOException {
		out.write(newline);
		indented = false;
	}

}
