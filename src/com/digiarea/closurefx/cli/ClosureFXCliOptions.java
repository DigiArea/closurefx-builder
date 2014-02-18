package com.digiarea.closurefx.cli;

public class ClosureFXCliOptions {

	private String closure;
	private boolean js = true;
	private boolean gss = true;
	private boolean soy = true;

	public String getClosure() {
		return closure;
	}

	public void setClosure(String closure) {
		this.closure = closure;
	}

	public boolean isJs() {
		return js;
	}

	public void setJs(boolean js) {
		this.js = js;
	}

	public boolean isGss() {
		return gss;
	}

	public void setGss(boolean gss) {
		this.gss = gss;
	}

	public boolean isSoy() {
		return soy;
	}

	public void setSoy(boolean soy) {
		this.soy = soy;
	}

}
