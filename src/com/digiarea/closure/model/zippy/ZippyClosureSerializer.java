package com.digiarea.closure.model.zippy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.digiarea.closure.model.Closure;
import com.digiarea.closurefx.IClosureSerializer;
import com.digiarea.zippy.ZippyBuffer;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class ZippyClosureSerializer implements IClosureSerializer {

	public Closure read(String path) throws Exception {
		if (path != null) {
			File file = new File(path);
			if (file.exists()) {
				byte[] buffer = Files.toByteArray(file);
				ZippyBuffer reader = new ZippyBuffer(buffer);
				Closure closure = Closure.readClosure(reader);
				return closure;
			}
		}
		return null;
	}

	public Closure read(InputStream stream) throws Exception {
		byte[] buffer = ByteStreams.toByteArray(stream);
		ZippyBuffer reader = new ZippyBuffer(buffer);
		Closure closure = Closure.readClosure(reader);
		return closure;
	}

	public void write(Closure closure, String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		int size = closure.sizeOfClosure(false);
		byte[] buffer = new byte[size];
		ZippyBuffer reader = new ZippyBuffer(buffer);
		closure.writeClosure(reader, false);
		FileOutputStream os = new FileOutputStream(file);
		os.write(buffer);
		os.close();
	}

}
