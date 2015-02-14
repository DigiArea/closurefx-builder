package com.digiarea.closure.preferences.model;

import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;

public enum OperatingSystem {

    LINUX_OS("Linux", OperatingSystemFamily.LINUX), 
    MAC_OSX("Mac OS X", OperatingSystemFamily.MAC), 
    MAC_OS("Mac OS", OperatingSystemFamily.MAC), 
    WINDOWS_95("Windows 95", OperatingSystemFamily.WINDOWS), 
    WINDOWS_98("Windows 98", OperatingSystemFamily.WINDOWS), 
    WINDOWS_ME("Windows Me", OperatingSystemFamily.WINDOWS), 
    WINDOWS_NT("Windows NT", OperatingSystemFamily.WINDOWS), 
    WINDOWS_2000("Windows 2000", OperatingSystemFamily.WINDOWS), 
    WINDOWS_XP("Windows XP", OperatingSystemFamily.WINDOWS), 
    WINDOWS_7("Windows 7", OperatingSystemFamily.WINDOWS), 
    WINDOWS_8("Windows 8", OperatingSystemFamily.WINDOWS), 
    WINDOWS_81("Windows 8.1", OperatingSystemFamily.WINDOWS), 
    WINDOWS_2003("Windows 2003", OperatingSystemFamily.WINDOWS), 
    WINDOWS_2008("Windows 2008", OperatingSystemFamily.WINDOWS), 
    SUN_OS("Sun OS ", OperatingSystemFamily.UNIX), 
    MPE_IX("MPE/iX", OperatingSystemFamily.UNIX), 
    HP_UX("HP-UX", OperatingSystemFamily.UNIX), 
    AIX("AIX", OperatingSystemFamily.UNIX), 
    OS_390("OS/390", OperatingSystemFamily.UNIX), 
    FREEBSD("FreeBSD", OperatingSystemFamily.UNIX), 
    IRIX("Irix", OperatingSystemFamily.UNIX), 
    DIGITAL_UNIX("Digital Unix", OperatingSystemFamily.UNIX), 
    NETWARE_4_11("NetWare 4.11", OperatingSystemFamily.UNIX), 
    OSF1("OSF1", OperatingSystemFamily.UNIX), 
    OPENVMS("OpenVMS", OperatingSystemFamily.DEC_OS);

    private final String label;

    private final OperatingSystemFamily family;

    private OperatingSystem(String label, OperatingSystemFamily family) {
        this.label = label;
        this.family = family;
    }

    public String getLabel() {
        return label;
    }

    public OperatingSystemFamily getFamily() {
        return family;
    }

    public static OperatingSystem resolve(String osName) {
        for (OperatingSystem os : OperatingSystem.values()) {
            if (os.label.equalsIgnoreCase(osName)) return os;
        }
        return null;
    }

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
