package com.digiarea.closurefx;

import com.digiarea.closure.model.zippy.ZippyClosureSerializer;

public class ClosureSerializerFactory {

	public static IClosureSerializer getSerializer() {
		// return new XMLClosureSerializer();
		return new ZippyClosureSerializer();
	}

}
