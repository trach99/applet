package Telna_MultiIMSI;

//import javax.net.ssl.CertPathTrustManagerParameters;

//import com.sun.org.apache.bcel.internal.generic.GOTO_W;

//import sim.access.SIMView;
//import sim.toolkit.MEProfile;
import uicc.access.FileView;
import uicc.access.UICCConstants;
import uicc.access.UICCSystem;
import uicc.toolkit.EnvelopeHandler;
import uicc.toolkit.EnvelopeHandlerSystem;
//import uicc.toolkit.EnvelopeResponseHandler;
import uicc.toolkit.ProactiveHandler;
import uicc.toolkit.ProactiveHandlerSystem;
import uicc.toolkit.ProactiveResponseHandler;
import uicc.toolkit.ProactiveResponseHandlerSystem;
import uicc.toolkit.ToolkitConstants;
import uicc.toolkit.ToolkitException;
import uicc.toolkit.ToolkitInterface;
import uicc.toolkit.ToolkitRegistry;
import uicc.toolkit.ToolkitRegistrySystem;
import javacard.framework.AID;
//import javacard.framework.ISO7816;
import javacard.framework.APDU;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Util;
import javacard.framework.Shareable;

public class Telna_MultiIMSI extends javacard.framework.Applet implements
ToolkitInterface, ToolkitConstants, uicc.usim.toolkit.ToolkitConstants {
	//-------------------NVM-------------------START
	//private byte[] MCCList = new byte[486];
	//private byte[] MCCIndex = new byte[243];
	/*
	private static final byte[]  ttt = {
		(byte)0x05, (byte)0x42, (byte)0x66, (byte)0x67, (byte)0x68, (byte)0x61, (byte)0x6E, (byte)0x69, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x41, (byte)0x6C, (byte)0x62, (byte)0x61, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x05, (byte)0x41, (byte)0x6C, (byte)0x67, (byte)0x65, (byte)0x72, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x41, (byte)0x6D, (byte)0x65, (byte)0x72, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0x6E, (byte)0x20, (byte)0x53, (byte)0x61, (byte)0x6D, (byte)0x6F, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x41, (byte)0x6E, (byte)0x64, (byte)0x6F, (byte)0x72, (byte)0x72, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x41, (byte)0x6E, (byte)0x67, (byte)0x6F, (byte)0x6C, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x41, (byte)0x6E, (byte)0x67, (byte)0x75, (byte)0x69, (byte)0x6C, (byte)0x6C, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x41, (byte)0x6E, (byte)0x74, (byte)0x69, (byte)0x67, (byte)0x75, (byte)0x61, (byte)0x20, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x20, (byte)0x42, (byte)0x61, (byte)0x72, (byte)0x62, (byte)0x75, (byte)0x64, (byte)0x61,
		(byte)0x09, (byte)0x41, (byte)0x72, (byte)0x67, (byte)0x65, (byte)0x6E, (byte)0x74, (byte)0x69, (byte)0x6E, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x41, (byte)0x72, (byte)0x6D, (byte)0x65, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x41, (byte)0x72, (byte)0x75, (byte)0x62, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x41, (byte)0x75, (byte)0x73, (byte)0x74, (byte)0x72, (byte)0x61, (byte)0x6C, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x41, (byte)0x75, (byte)0x73, (byte)0x74, (byte)0x72, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x41, (byte)0x7A, (byte)0x65, (byte)0x72, (byte)0x62, (byte)0x61, (byte)0x69, (byte)0x6A, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x42, (byte)0x61, (byte)0x68, (byte)0x61, (byte)0x6D, (byte)0x61, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x42, (byte)0x61, (byte)0x68, (byte)0x72, (byte)0x61, (byte)0x69, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x42, (byte)0x61, (byte)0x6E, (byte)0x67, (byte)0x6C, (byte)0x61, (byte)0x64, (byte)0x65, (byte)0x73, (byte)0x68, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x42, (byte)0x61, (byte)0x72, (byte)0x62, (byte)0x61, (byte)0x64, (byte)0x6F, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x42, (byte)0x65, (byte)0x6C, (byte)0x61, (byte)0x72, (byte)0x75, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x42, (byte)0x65, (byte)0x6C, (byte)0x67, (byte)0x69, (byte)0x75, (byte)0x6D, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x42, (byte)0x65, (byte)0x6C, (byte)0x69, (byte)0x7A, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x42, (byte)0x65, (byte)0x6E, (byte)0x69, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x42, (byte)0x65, (byte)0x72, (byte)0x6D, (byte)0x75, (byte)0x64, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x42, (byte)0x68, (byte)0x75, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x42, (byte)0x6F, (byte)0x6C, (byte)0x69, (byte)0x76, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x42, (byte)0x6F, (byte)0x73, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0x20, (byte)0x26, (byte)0x20, (byte)0x48, (byte)0x65, (byte)0x72, (byte)0x7A, (byte)0x65, (byte)0x67, (byte)0x6F, (byte)0x76, (byte)0x69, (byte)0x6E,
		(byte)0x07, (byte)0x42, (byte)0x6F, (byte)0x74, (byte)0x73, (byte)0x77, (byte)0x61, (byte)0x6E, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x42, (byte)0x72, (byte)0x61, (byte)0x7A, (byte)0x69, (byte)0x6C, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x42, (byte)0x72, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0x73, (byte)0x68, (byte)0x20, (byte)0x56, (byte)0x69, (byte)0x72, (byte)0x2E, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64,
		(byte)0x06, (byte)0x42, (byte)0x72, (byte)0x75, (byte)0x6E, (byte)0x65, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x42, (byte)0x75, (byte)0x6C, (byte)0x67, (byte)0x61, (byte)0x72, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x05, (byte)0x42, (byte)0x75, (byte)0x72, (byte)0x6B, (byte)0x69, (byte)0x6E, (byte)0x61, (byte)0x20, (byte)0x46, (byte)0x61, (byte)0x73, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x42, (byte)0x75, (byte)0x72, (byte)0x75, (byte)0x6E, (byte)0x64, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x43, (byte)0x61, (byte)0x6D, (byte)0x62, (byte)0x6F, (byte)0x64, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x43, (byte)0x61, (byte)0x6D, (byte)0x65, (byte)0x72, (byte)0x6F, (byte)0x6F, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x43, (byte)0x61, (byte)0x6E, (byte)0x61, (byte)0x64, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x43, (byte)0x61, (byte)0x70, (byte)0x65, (byte)0x20, (byte)0x56, (byte)0x65, (byte)0x72, (byte)0x64, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x43, (byte)0x61, (byte)0x79, (byte)0x6D, (byte)0x61, (byte)0x6E, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x07, (byte)0x43, (byte)0x65, (byte)0x6E, (byte)0x74, (byte)0x72, (byte)0x20, (byte)0x41, (byte)0x66, (byte)0x72, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0x6E, (byte)0x20, (byte)0x52, (byte)0x65, (byte)0x70, (byte)0x75, (byte)0x62,
		(byte)0x01, (byte)0x43, (byte)0x68, (byte)0x61, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x43, (byte)0x68, (byte)0x69, (byte)0x6C, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x43, (byte)0x68, (byte)0x69, (byte)0x6E, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x43, (byte)0x6F, (byte)0x6C, (byte)0x6F, (byte)0x6D, (byte)0x62, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x43, (byte)0x6F, (byte)0x6D, (byte)0x6F, (byte)0x72, (byte)0x6F, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x43, (byte)0x6F, (byte)0x6F, (byte)0x6B, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x43, (byte)0x6F, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x20, (byte)0x52, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x43, (byte)0x72, (byte)0x6F, (byte)0x61, (byte)0x74, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x43, (byte)0x75, (byte)0x62, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x43, (byte)0x75, (byte)0x72, (byte)0x61, (byte)0x63, (byte)0x61, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x43, (byte)0x79, (byte)0x70, (byte)0x72, (byte)0x75, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x43, (byte)0x7A, (byte)0x65, (byte)0x63, (byte)0x68, (byte)0x20, (byte)0x52, (byte)0x65, (byte)0x70, (byte)0x75, (byte)0x62, (byte)0x6C, (byte)0x69, (byte)0x63, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x44, (byte)0x65, (byte)0x6D, (byte)0x6F, (byte)0x63, (byte)0x72, (byte)0x20, (byte)0x52, (byte)0x65, (byte)0x70, (byte)0x20, (byte)0x6F, (byte)0x66, (byte)0x20, (byte)0x43, (byte)0x6F, (byte)0x6E, (byte)0x67, (byte)0x6F,
		(byte)0x04, (byte)0x44, (byte)0x65, (byte)0x6E, (byte)0x6D, (byte)0x61, (byte)0x72, (byte)0x6B, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x44, (byte)0x6A, (byte)0x69, (byte)0x62, (byte)0x6F, (byte)0x75, (byte)0x74, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x44, (byte)0x6F, (byte)0x6D, (byte)0x69, (byte)0x6E, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x44, (byte)0x6F, (byte)0x6D, (byte)0x69, (byte)0x6E, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0x6E, (byte)0x20, (byte)0x52, (byte)0x65, (byte)0x70, (byte)0x75, (byte)0x62, (byte)0x6C, (byte)0x69, (byte)0x63, (byte)0xFF,
		(byte)0x09, (byte)0x45, (byte)0x63, (byte)0x75, (byte)0x61, (byte)0x64, (byte)0x6F, (byte)0x72, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x45, (byte)0x67, (byte)0x79, (byte)0x70, (byte)0x74, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x45, (byte)0x6C, (byte)0x20, (byte)0x53, (byte)0x61, (byte)0x6C, (byte)0x76, (byte)0x61, (byte)0x64, (byte)0x6F, (byte)0x72, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x45, (byte)0x71, (byte)0x75, (byte)0x61, (byte)0x74, (byte)0x6F, (byte)0x72, (byte)0x69, (byte)0x61, (byte)0x6C, (byte)0x20, (byte)0x47, (byte)0x75, (byte)0x69, (byte)0x6E, (byte)0x65, (byte)0x61, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x45, (byte)0x72, (byte)0x69, (byte)0x74, (byte)0x72, (byte)0x65, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x45, (byte)0x73, (byte)0x74, (byte)0x6F, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x45, (byte)0x74, (byte)0x68, (byte)0x69, (byte)0x6F, (byte)0x70, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x46, (byte)0x61, (byte)0x6C, (byte)0x6B, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x46, (byte)0x61, (byte)0x72, (byte)0x6F, (byte)0x65, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x46, (byte)0x69, (byte)0x6A, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x46, (byte)0x69, (byte)0x6E, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x46, (byte)0x72, (byte)0x61, (byte)0x6E, (byte)0x63, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x46, (byte)0x72, (byte)0x65, (byte)0x6E, (byte)0x63, (byte)0x68, (byte)0x20, (byte)0x47, (byte)0x75, (byte)0x69, (byte)0x61, (byte)0x6E, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x46, (byte)0x72, (byte)0x65, (byte)0x6E, (byte)0x63, (byte)0x68, (byte)0x20, (byte)0x50, (byte)0x6F, (byte)0x6C, (byte)0x79, (byte)0x6E, (byte)0x65, (byte)0x73, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x47, (byte)0x61, (byte)0x62, (byte)0x6F, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x47, (byte)0x61, (byte)0x6D, (byte)0x62, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x47, (byte)0x65, (byte)0x6F, (byte)0x72, (byte)0x67, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x47, (byte)0x65, (byte)0x72, (byte)0x6D, (byte)0x61, (byte)0x6E, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x47, (byte)0x68, (byte)0x61, (byte)0x6E, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x47, (byte)0x69, (byte)0x62, (byte)0x72, (byte)0x61, (byte)0x6C, (byte)0x74, (byte)0x61, (byte)0x72, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x47, (byte)0x72, (byte)0x65, (byte)0x65, (byte)0x63, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x47, (byte)0x72, (byte)0x65, (byte)0x65, (byte)0x6E, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x47, (byte)0x72, (byte)0x65, (byte)0x6E, (byte)0x61, (byte)0x64, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x47, (byte)0x75, (byte)0x61, (byte)0x64, (byte)0x65, (byte)0x6C, (byte)0x6F, (byte)0x75, (byte)0x70, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x47, (byte)0x75, (byte)0x61, (byte)0x6D, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x47, (byte)0x75, (byte)0x61, (byte)0x74, (byte)0x65, (byte)0x6D, (byte)0x61, (byte)0x6C, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x47, (byte)0x75, (byte)0x65, (byte)0x72, (byte)0x6E, (byte)0x73, (byte)0x65, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x47, (byte)0x75, (byte)0x69, (byte)0x6E, (byte)0x65, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x47, (byte)0x75, (byte)0x69, (byte)0x6E, (byte)0x65, (byte)0x61, (byte)0x2D, (byte)0x42, (byte)0x69, (byte)0x73, (byte)0x73, (byte)0x61, (byte)0x75, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x47, (byte)0x75, (byte)0x79, (byte)0x61, (byte)0x6E, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x48, (byte)0x61, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x48, (byte)0x6F, (byte)0x6E, (byte)0x64, (byte)0x75, (byte)0x72, (byte)0x61, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x48, (byte)0x6F, (byte)0x6E, (byte)0x67, (byte)0x20, (byte)0x4B, (byte)0x6F, (byte)0x6E, (byte)0x67, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x48, (byte)0x75, (byte)0x6E, (byte)0x67, (byte)0x61, (byte)0x72, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x49, (byte)0x6E, (byte)0x64, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x49, (byte)0x6E, (byte)0x64, (byte)0x6F, (byte)0x6E, (byte)0x65, (byte)0x73, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x49, (byte)0x72, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x49, (byte)0x72, (byte)0x61, (byte)0x71, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x49, (byte)0x72, (byte)0x65, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x65, (byte)0x20, (byte)0x6F, (byte)0x66, (byte)0x20, (byte)0x4D, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x49, (byte)0x73, (byte)0x72, (byte)0x61, (byte)0x65, (byte)0x6C, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x49, (byte)0x74, (byte)0x61, (byte)0x6C, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x49, (byte)0x76, (byte)0x6F, (byte)0x72, (byte)0x79, (byte)0x20, (byte)0x43, (byte)0x6F, (byte)0x61, (byte)0x73, (byte)0x74, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4A, (byte)0x61, (byte)0x6D, (byte)0x61, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4A, (byte)0x61, (byte)0x70, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4A, (byte)0x65, (byte)0x72, (byte)0x73, (byte)0x65, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4A, (byte)0x6F, (byte)0x72, (byte)0x64, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4B, (byte)0x61, (byte)0x7A, (byte)0x61, (byte)0x6B, (byte)0x68, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4B, (byte)0x65, (byte)0x6E, (byte)0x79, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4B, (byte)0x69, (byte)0x72, (byte)0x69, (byte)0x62, (byte)0x61, (byte)0x74, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4B, (byte)0x6F, (byte)0x73, (byte)0x6F, (byte)0x76, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4B, (byte)0x75, (byte)0x77, (byte)0x61, (byte)0x69, (byte)0x74, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4B, (byte)0x79, (byte)0x72, (byte)0x67, (byte)0x79, (byte)0x7A, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4C, (byte)0x61, (byte)0x6F, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4C, (byte)0x61, (byte)0x74, (byte)0x76, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x05, (byte)0x4C, (byte)0x65, (byte)0x62, (byte)0x61, (byte)0x6E, (byte)0x6F, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4C, (byte)0x65, (byte)0x73, (byte)0x6F, (byte)0x74, (byte)0x68, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4C, (byte)0x69, (byte)0x62, (byte)0x65, (byte)0x72, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4C, (byte)0x69, (byte)0x62, (byte)0x79, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4C, (byte)0x69, (byte)0x65, (byte)0x63, (byte)0x68, (byte)0x74, (byte)0x65, (byte)0x6E, (byte)0x73, (byte)0x74, (byte)0x65, (byte)0x69, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4C, (byte)0x69, (byte)0x74, (byte)0x68, (byte)0x75, (byte)0x61, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4C, (byte)0x75, (byte)0x78, (byte)0x65, (byte)0x6D, (byte)0x62, (byte)0x6F, (byte)0x75, (byte)0x72, (byte)0x67, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x4D, (byte)0x61, (byte)0x63, (byte)0x61, (byte)0x75, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4D, (byte)0x61, (byte)0x63, (byte)0x65, (byte)0x64, (byte)0x6F, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4D, (byte)0x61, (byte)0x64, (byte)0x61, (byte)0x67, (byte)0x61, (byte)0x73, (byte)0x63, (byte)0x61, (byte)0x72, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4D, (byte)0x61, (byte)0x6C, (byte)0x61, (byte)0x77, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x4D, (byte)0x61, (byte)0x6C, (byte)0x61, (byte)0x79, (byte)0x73, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x05, (byte)0x4D, (byte)0x61, (byte)0x6C, (byte)0x64, (byte)0x69, (byte)0x76, (byte)0x65, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x07, (byte)0x4D, (byte)0x61, (byte)0x6C, (byte)0x69, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4D, (byte)0x61, (byte)0x6C, (byte)0x74, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4D, (byte)0x61, (byte)0x72, (byte)0x74, (byte)0x69, (byte)0x6E, (byte)0x69, (byte)0x71, (byte)0x75, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4D, (byte)0x61, (byte)0x75, (byte)0x72, (byte)0x69, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x4D, (byte)0x61, (byte)0x75, (byte)0x72, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0x75, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x4D, (byte)0x65, (byte)0x78, (byte)0x69, (byte)0x63, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4D, (byte)0x69, (byte)0x63, (byte)0x72, (byte)0x6F, (byte)0x6E, (byte)0x65, (byte)0x73, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4D, (byte)0x6F, (byte)0x6C, (byte)0x64, (byte)0x6F, (byte)0x76, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4D, (byte)0x6F, (byte)0x6E, (byte)0x61, (byte)0x63, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4D, (byte)0x6F, (byte)0x6E, (byte)0x67, (byte)0x6F, (byte)0x6C, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4D, (byte)0x6F, (byte)0x6E, (byte)0x74, (byte)0x65, (byte)0x6E, (byte)0x65, (byte)0x67, (byte)0x72, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4D, (byte)0x6F, (byte)0x6E, (byte)0x74, (byte)0x73, (byte)0x65, (byte)0x72, (byte)0x72, (byte)0x61, (byte)0x74, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4D, (byte)0x6F, (byte)0x72, (byte)0x6F, (byte)0x63, (byte)0x63, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x4D, (byte)0x6F, (byte)0x7A, (byte)0x61, (byte)0x6D, (byte)0x62, (byte)0x69, (byte)0x71, (byte)0x75, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x4D, (byte)0x79, (byte)0x61, (byte)0x6E, (byte)0x6D, (byte)0x61, (byte)0x72, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4E, (byte)0x61, (byte)0x67, (byte)0x6F, (byte)0x72, (byte)0x6E, (byte)0x79, (byte)0x20, (byte)0x4B, (byte)0x61, (byte)0x72, (byte)0x61, (byte)0x62, (byte)0x61, (byte)0x6B, (byte)0x68, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4E, (byte)0x61, (byte)0x6D, (byte)0x69, (byte)0x62, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4E, (byte)0x61, (byte)0x75, (byte)0x72, (byte)0x75, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4E, (byte)0x65, (byte)0x70, (byte)0x61, (byte)0x6C, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4E, (byte)0x65, (byte)0x74, (byte)0x68, (byte)0x65, (byte)0x72, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x4E, (byte)0x65, (byte)0x77, (byte)0x20, (byte)0x43, (byte)0x61, (byte)0x6C, (byte)0x65, (byte)0x64, (byte)0x6F, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x4E, (byte)0x65, (byte)0x77, (byte)0x20, (byte)0x5A, (byte)0x65, (byte)0x61, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x4E, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0x72, (byte)0x61, (byte)0x67, (byte)0x75, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x4E, (byte)0x69, (byte)0x67, (byte)0x65, (byte)0x72, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x4E, (byte)0x69, (byte)0x67, (byte)0x65, (byte)0x72, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x4E, (byte)0x6F, (byte)0x72, (byte)0x77, (byte)0x61, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x4F, (byte)0x6D, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x50, (byte)0x61, (byte)0x6B, (byte)0x69, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x50, (byte)0x61, (byte)0x6C, (byte)0x61, (byte)0x75, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x05, (byte)0x50, (byte)0x61, (byte)0x6C, (byte)0x65, (byte)0x73, (byte)0x74, (byte)0x69, (byte)0x6E, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x50, (byte)0x61, (byte)0x6E, (byte)0x61, (byte)0x6D, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x50, (byte)0x61, (byte)0x70, (byte)0x75, (byte)0x61, (byte)0x20, (byte)0x4E, (byte)0x65, (byte)0x77, (byte)0x20, (byte)0x47, (byte)0x75, (byte)0x69, (byte)0x6E, (byte)0x65, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x50, (byte)0x61, (byte)0x72, (byte)0x61, (byte)0x67, (byte)0x75, (byte)0x61, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x50, (byte)0x65, (byte)0x72, (byte)0x75, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x50, (byte)0x68, (byte)0x69, (byte)0x6C, (byte)0x69, (byte)0x70, (byte)0x70, (byte)0x69, (byte)0x6E, (byte)0x65, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x50, (byte)0x6F, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x50, (byte)0x6F, (byte)0x72, (byte)0x74, (byte)0x75, (byte)0x67, (byte)0x61, (byte)0x6C, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x50, (byte)0x75, (byte)0x65, (byte)0x72, (byte)0x74, (byte)0x6F, (byte)0x20, (byte)0x52, (byte)0x69, (byte)0x63, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x51, (byte)0x61, (byte)0x74, (byte)0x61, (byte)0x72, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x52, (byte)0x65, (byte)0x70, (byte)0x20, (byte)0x6F, (byte)0x66, (byte)0x20, (byte)0x43, (byte)0x6F, (byte)0x6E, (byte)0x67, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x52, (byte)0x65, (byte)0x75, (byte)0x6E, (byte)0x69, (byte)0x6F, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x52, (byte)0x6F, (byte)0x6D, (byte)0x61, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x52, (byte)0x75, (byte)0x73, (byte)0x73, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x52, (byte)0x77, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x53, (byte)0x74, (byte)0x2E, (byte)0x20, (byte)0x4B, (byte)0x69, (byte)0x74, (byte)0x74, (byte)0x73, (byte)0x20, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x20, (byte)0x4E, (byte)0x65, (byte)0x76, (byte)0x69, (byte)0x73,
		(byte)0x01, (byte)0x53, (byte)0x61, (byte)0x69, (byte)0x6E, (byte)0x74, (byte)0x20, (byte)0x4C, (byte)0x75, (byte)0x63, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x61, (byte)0x69, (byte)0x6E, (byte)0x74, (byte)0x20, (byte)0x4D, (byte)0x61, (byte)0x61, (byte)0x72, (byte)0x74, (byte)0x65, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x61, (byte)0x69, (byte)0x6E, (byte)0x74, (byte)0x20, (byte)0x4D, (byte)0x61, (byte)0x72, (byte)0x74, (byte)0x69, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x61, (byte)0x69, (byte)0x6E, (byte)0x74, (byte)0x20, (byte)0x50, (byte)0x69, (byte)0x65, (byte)0x72, (byte)0x72, (byte)0x65, (byte)0x20, (byte)0x26, (byte)0x20, (byte)0x4D, (byte)0x69, (byte)0x71, (byte)0x75,
		(byte)0x01, (byte)0x53, (byte)0x61, (byte)0x6D, (byte)0x6F, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x61, (byte)0x6E, (byte)0x20, (byte)0x4D, (byte)0x61, (byte)0x72, (byte)0x69, (byte)0x6E, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x61, (byte)0x6F, (byte)0x20, (byte)0x54, (byte)0x6F, (byte)0x6D, (byte)0x65, (byte)0x20, (byte)0x26, (byte)0x20, (byte)0x50, (byte)0x72, (byte)0x69, (byte)0x6E, (byte)0x63, (byte)0x69, (byte)0x70, (byte)0x65,
		(byte)0x06, (byte)0x53, (byte)0x61, (byte)0x75, (byte)0x64, (byte)0x69, (byte)0x20, (byte)0x41, (byte)0x72, (byte)0x61, (byte)0x62, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x07, (byte)0x53, (byte)0x65, (byte)0x6E, (byte)0x65, (byte)0x67, (byte)0x61, (byte)0x6C, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x53, (byte)0x65, (byte)0x72, (byte)0x62, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x53, (byte)0x65, (byte)0x79, (byte)0x63, (byte)0x68, (byte)0x65, (byte)0x6C, (byte)0x6C, (byte)0x65, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x68, (byte)0x69, (byte)0x70, (byte)0x2F, (byte)0x50, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x53, (byte)0x69, (byte)0x65, (byte)0x72, (byte)0x72, (byte)0x61, (byte)0x20, (byte)0x4C, (byte)0x65, (byte)0x6F, (byte)0x6E, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x53, (byte)0x69, (byte)0x6E, (byte)0x67, (byte)0x61, (byte)0x70, (byte)0x6F, (byte)0x72, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x53, (byte)0x6C, (byte)0x6F, (byte)0x76, (byte)0x61, (byte)0x6B, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x53, (byte)0x6C, (byte)0x6F, (byte)0x76, (byte)0x65, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x6F, (byte)0x6C, (byte)0x6F, (byte)0x6D, (byte)0x6F, (byte)0x6E, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x6F, (byte)0x6D, (byte)0x61, (byte)0x6C, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x53, (byte)0x6F, (byte)0x75, (byte)0x74, (byte)0x68, (byte)0x20, (byte)0x41, (byte)0x66, (byte)0x72, (byte)0x69, (byte)0x63, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x53, (byte)0x6F, (byte)0x75, (byte)0x74, (byte)0x68, (byte)0x20, (byte)0x4B, (byte)0x6F, (byte)0x72, (byte)0x65, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x6F, (byte)0x75, (byte)0x74, (byte)0x68, (byte)0x20, (byte)0x53, (byte)0x75, (byte)0x64, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x53, (byte)0x70, (byte)0x61, (byte)0x69, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x53, (byte)0x72, (byte)0x69, (byte)0x20, (byte)0x4C, (byte)0x61, (byte)0x6E, (byte)0x6B, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x53, (byte)0x74, (byte)0x2E, (byte)0x20, (byte)0x56, (byte)0x69, (byte)0x6E, (byte)0x63, (byte)0x65, (byte)0x6E, (byte)0x74, (byte)0x20, (byte)0x26, (byte)0x20, (byte)0x47, (byte)0x72, (byte)0x65, (byte)0x6E, (byte)0x61,
		(byte)0x02, (byte)0x53, (byte)0x75, (byte)0x64, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x53, (byte)0x75, (byte)0x72, (byte)0x69, (byte)0x6E, (byte)0x61, (byte)0x6D, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x77, (byte)0x61, (byte)0x7A, (byte)0x69, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x53, (byte)0x77, (byte)0x65, (byte)0x64, (byte)0x65, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x53, (byte)0x77, (byte)0x69, (byte)0x74, (byte)0x7A, (byte)0x65, (byte)0x72, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x53, (byte)0x79, (byte)0x72, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x54, (byte)0x61, (byte)0x69, (byte)0x77, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x54, (byte)0x61, (byte)0x6A, (byte)0x69, (byte)0x6B, (byte)0x69, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x54, (byte)0x61, (byte)0x6E, (byte)0x7A, (byte)0x61, (byte)0x6E, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x54, (byte)0x68, (byte)0x61, (byte)0x69, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x54, (byte)0x69, (byte)0x6D, (byte)0x6F, (byte)0x72, (byte)0x2D, (byte)0x4C, (byte)0x65, (byte)0x73, (byte)0x74, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x54, (byte)0x6F, (byte)0x67, (byte)0x6F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x54, (byte)0x6F, (byte)0x6E, (byte)0x67, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x54, (byte)0x72, (byte)0x69, (byte)0x6E, (byte)0x69, (byte)0x64, (byte)0x61, (byte)0x64, (byte)0x20, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x20, (byte)0x54, (byte)0x6F, (byte)0x62, (byte)0x61, (byte)0x67, (byte)0x6F,
		(byte)0x01, (byte)0x54, (byte)0x75, (byte)0x6E, (byte)0x69, (byte)0x73, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x54, (byte)0x75, (byte)0x72, (byte)0x6B, (byte)0x65, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x54, (byte)0x75, (byte)0x72, (byte)0x6B, (byte)0x6D, (byte)0x65, (byte)0x6E, (byte)0x69, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x54, (byte)0x75, (byte)0x72, (byte)0x6B, (byte)0x6D, (byte)0x65, (byte)0x6E, (byte)0x69, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x54, (byte)0x75, (byte)0x72, (byte)0x6B, (byte)0x73, (byte)0x20, (byte)0x26, (byte)0x20, (byte)0x43, (byte)0x61, (byte)0x69, (byte)0x63, (byte)0x6F, (byte)0x73, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61,
		(byte)0x02, (byte)0x55, (byte)0x2E, (byte)0x53, (byte)0x2E, (byte)0x20, (byte)0x56, (byte)0x69, (byte)0x72, (byte)0x67, (byte)0x69, (byte)0x6E, (byte)0x20, (byte)0x49, (byte)0x73, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x73,
		(byte)0x07, (byte)0x55, (byte)0x41, (byte)0x45, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x55, (byte)0x67, (byte)0x61, (byte)0x6E, (byte)0x64, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x55, (byte)0x6B, (byte)0x72, (byte)0x61, (byte)0x69, (byte)0x6E, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x04, (byte)0x55, (byte)0x6E, (byte)0x69, (byte)0x74, (byte)0x65, (byte)0x64, (byte)0x20, (byte)0x4B, (byte)0x69, (byte)0x6E, (byte)0x67, (byte)0x64, (byte)0x6F, (byte)0x6D, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x55, (byte)0x6E, (byte)0x69, (byte)0x74, (byte)0x65, (byte)0x64, (byte)0x20, (byte)0x53, (byte)0x74, (byte)0x61, (byte)0x74, (byte)0x65, (byte)0x73, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x09, (byte)0x55, (byte)0x72, (byte)0x75, (byte)0x67, (byte)0x75, (byte)0x61, (byte)0x79, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x55, (byte)0x7A, (byte)0x62, (byte)0x65, (byte)0x6B, (byte)0x69, (byte)0x73, (byte)0x74, (byte)0x61, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x56, (byte)0x61, (byte)0x6E, (byte)0x75, (byte)0x61, (byte)0x74, (byte)0x75, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x03, (byte)0x56, (byte)0x65, (byte)0x6E, (byte)0x65, (byte)0x7A, (byte)0x75, (byte)0x65, (byte)0x6C, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x08, (byte)0x56, (byte)0x69, (byte)0x65, (byte)0x74, (byte)0x6E, (byte)0x61, (byte)0x6D, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x02, (byte)0x59, (byte)0x65, (byte)0x6D, (byte)0x65, (byte)0x6E, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x06, (byte)0x5A, (byte)0x61, (byte)0x6D, (byte)0x62, (byte)0x69, (byte)0x61, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
		(byte)0x01, (byte)0x5A, (byte)0x69, (byte)0x6D, (byte)0x62, (byte)0x61, (byte)0x62, (byte)0x77, (byte)0x65, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
	 */
	private static final byte [] PROFILE =
		{
		(byte)21, // POLL INTERVAL
		//(byte)23, // REFRESH
		//(byte)30, // Provide local information
		//(byte)56 // Timer Management (Start Stop) = 56, Get current value  = 57
		};
	private static final byte[]  ChangeHandset = {(byte)'T', (byte)'h', (byte)'e', (byte)' ', (byte)'H', (byte)'a', (byte)'n', (byte)'d', (byte)'s', (byte)'e', (byte)'t', (byte)' ', (byte)'d', (byte)'o', (byte)'e', (byte)'s', (byte)'n', (byte)'’', (byte)'t', (byte)' ', (byte)'s', (byte)'u', (byte)'p', (byte)'p', (byte)'o', (byte)'r', (byte)'t', (byte)' ', (byte)'M', (byte)'u', (byte)'l', (byte)'t', (byte)'i', (byte)'-', (byte)'I', (byte)'M', (byte)'S', (byte)'I', (byte)' ', (byte)'s', (byte)'w', (byte)'i', (byte)'t', (byte)'c', (byte)'h', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'c', (byte)'a', (byte)'p', (byte)'a', (byte)'b', (byte)'i', (byte)'l', (byte)'i', (byte)'t', (byte)'y', (byte)'.', (byte)' ', (byte)'P', (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'e', (byte)' ', (byte)'c', (byte)'h', (byte)'a', (byte)'n', (byte)'g', (byte)'e', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'H', (byte)'a', (byte)'n', (byte)'d', (byte)'s', (byte)'e', (byte)'t', (byte)' ', (byte)'o', (byte)'r', (byte)' ', (byte)'u', (byte)'s', (byte)'e', (byte)' ', (byte)'M', (byte)'a', (byte)'n', (byte)'u', (byte)'a', (byte)'l', (byte)' ', (byte)'S', (byte)'w', (byte)'i', (byte)'t', (byte)'c', (byte)'h', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'v', (byte)'i', (byte)'a', (byte)' ', (byte)'S', (byte)'T', (byte)'K', (byte)' ', (byte)'o', (byte)'n', (byte)'l', (byte)'y', (byte)'.'};
	private static final byte[]  DT_RestartHandset = {(byte)'P', (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'e', (byte)' ', (byte)'R', (byte)'e', (byte)'s', (byte)'t', (byte)'a', (byte)'r', (byte)'t', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'H', (byte)'a', (byte)'n', (byte)'d', (byte)'s', (byte)'e', (byte)'t'};
	//private static final byte[]  DT_ConnectNetwrk = {(byte)'P', (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'e', (byte)' ', (byte)'n', (byte)'a', (byte)'v', (byte)'i', (byte)'g', (byte)'a', (byte)'t', (byte)'e', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'S', (byte)'I', (byte)'M', (byte)' ', (byte)'T', (byte)'o', (byte)'o', (byte)'l', (byte)'k', (byte)'i', (byte)'t', (byte)' ', (byte)'m', (byte)'e', (byte)'n', (byte)'u', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'m', (byte)'a', (byte)'n', (byte)'u', (byte)'a', (byte)'l', (byte)'l', (byte)'y', (byte)' ', (byte)'C', (byte)'o', (byte)'n', (byte)'n', (byte)'e', (byte)'c', (byte)'t', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'n', (byte)'e', (byte)'t', (byte)'w', (byte)'o', (byte)'r', (byte)'k', (byte)'.'};
	private static final byte[]  DT_AutoDisabled = {(byte)'A', (byte)'u', (byte)'t', (byte)'o', (byte)'m', (byte)'a', (byte)'t', (byte)'i', (byte)'c', (byte)' ', (byte)'N', (byte)'e', (byte)'t', (byte)'w', (byte)'o', (byte)'r', (byte)'k', (byte)' ', (byte)'s', (byte)'w', (byte)'i', (byte)'t', (byte)'c', (byte)'h', (byte)' ', (byte)'m', (byte)'o', (byte)'d', (byte)'e', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'D', (byte)'i', (byte)'s', (byte)'a', (byte)'b', (byte)'l', (byte)'e', (byte)'d', (byte)'.', (byte)' ', (byte)'P', (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'e', (byte)' ', (byte)'E', (byte)'n', (byte)'a', (byte)'b', (byte)'l', (byte)'e', (byte)' ', (byte)'i', (byte)'t', (byte)' ', (byte)'f', (byte)'r', (byte)'o', (byte)'m', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'S', (byte)'I', (byte)'M', (byte)' ', (byte)'T', (byte)'o', (byte)'o', (byte)'l', (byte)'k', (byte)'i', (byte)'t', (byte)' ', (byte)'M', (byte)'e', (byte)'n', (byte)'u', (byte)'.'};
	private boolean DT_AutoDisabled_flag=false;
	//private static final byte[]  DT_SwitchNW = {(byte)'S', (byte)'w', (byte)'i', (byte)'t', (byte)'c', (byte)'h', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'N', (byte)'e', (byte)'t', (byte)'w', (byte)'o', (byte)'r', (byte)'k', (byte)'.', (byte)'.', (byte)'.'};

	private static final short DF_WorkzMI = (short) 0x9009;
	private static final short EF_MI_Multi_Settings = (short) 0x0001;
	private static final short EF_MI_Multi_IMSI = (short) 0x2601;
	private static final short EF_MI_Multi_SMSC = (short) 0x2602;
	private static final short EF_MI_PLMN_MAP = (short) 0x2603;
	private static final short EF_MI_IMSI_NUM = (short) 0x260B;
	private static final short EF_MI_COUNTRY_LIST = (short) 0x260C;
	private static final short EF_MI_IMSI_Status = (short) 0x260D;
	private static final short EF_MI_MNC_Len = (short) 0x260E;
	private static final short EF_MI_PPLMNWACT = (short) 0x260F;
	private static final short EF_MI_GID1 = (short) 0x6F3E;
	private static final short EF_MI_GID2 = (short) 0x6F3F;

	private static final short MCC_List_SIZE = (short)1100;
	private static short SMSP_SIZE = (short)40;
	private static short IMSI_FILE_COUNT = (short)20;
	private static short PPLMN_Size = (short)125;
	private static short GID1_Size = (short)15;
	private static short GID2_Size = (short)15;
	private static short Country_COUNT = (short)226;
	private static short Country_SIZE = (short)20;
	private static final short Country_Display = (short)10;
	private static final byte[]  MCC_List = new byte[MCC_List_SIZE];
	//private static final byte[]  mcc_sel = {(byte)0x02, (byte)0x03, (byte)0x04, (byte)0x07, (byte)0x08, (byte)0x0A, (byte)0x11, (byte)0x12, (byte)0x13};

	private static final byte[]  Country = {(byte)'C', (byte)'o', (byte)'u', (byte)'n', (byte)'t', (byte)'r', (byte)'y'};
	private static final byte[]  Mode = {(byte)'M', (byte)'o', (byte)'d', (byte)'e'};
	private static final byte[]  ICCID = {(byte)'I', (byte)'C', (byte)'C', (byte)'I', (byte)'D'};
	private static final byte[]  back = {(byte)'<', (byte)'<'};
	private static final byte[]  next = {(byte)'>', (byte)'>'};
	private static final byte[]  Auto = {(byte)'*', (byte)'A', (byte)'u', (byte)'t', (byte)'o'};
	private static final byte[]  Manual = {(byte)'*', (byte)'M', (byte)'a', (byte)'n', (byte)'u', (byte)'a', (byte)'l'};
	private static final byte[]  MIMSI = {(byte)'*', (byte)'I', (byte)'M', (byte)'S', (byte)'I', (byte)' '};
	private static final byte[]  on = {(byte)'*', (byte)'O', (byte)'N'};
	private static final byte[]  off = {(byte)'*', (byte)'O', (byte)'F', (byte)'F'};

	//private static final byte[]  AutoMode = {(byte)'*', (byte)'A', (byte)'u', (byte)'t', (byte)'o', (byte)'m', (byte)'a', (byte)'t', (byte)'i', (byte)'c', (byte)' ', (byte)'N', (byte)'e', (byte)'t', (byte)'w', (byte)'o', (byte)'r', (byte)'k', (byte)' ', (byte)'M', (byte)'o', (byte)'d', (byte)'e'};
	//private static final byte[]  ManMode = {(byte)'*', (byte)'C', (byte)'o', (byte)'n', (byte)'n', (byte)'e', (byte)'c', (byte)'t', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'N', (byte)'e', (byte)'t', (byte)'w', (byte)'o', (byte)'r', (byte)'k'};
	//private static final byte[]  ManMode = {(byte)'*', (byte)'C', (byte)'o', (byte)'n', (byte)'n', (byte)'e', (byte)'c', (byte)'t', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'N', (byte)'e', (byte)'t', (byte)'w', (byte)'o', (byte)'r', (byte)'k', (byte)' ', (byte)'(', (byte)'M', (byte)'a', (byte)'n', (byte)'u', (byte)'a', (byte)'l', (byte)')'};
	//private static final byte[]  SelectIMSI = {(byte)'S', (byte)'e', (byte)'l', (byte)'e', (byte)'c', (byte)'t', (byte)' ', (byte)'I', (byte)'M', (byte)'S', (byte)'I'};
	private static final byte[]  IMSI_FF = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
	//private static final byte[]  IMSI_6F73 = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0xFF, (byte)0x01};
	//private static final byte[]  IMSI_6F7E = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0xFF, (byte)0x01};
	//private static final byte[]  IMSI_6FE3 = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0x01};
	//private static final byte[]  IMSI_not_config = {(byte)'T', (byte)'h', (byte)'e', (byte)' ', (byte)'I', (byte)'M', (byte)'S', (byte)'I', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'n', (byte)'o', (byte)'t', (byte)' ', (byte)'y', (byte)'e', (byte)'t', (byte)' ', (byte)'c', (byte)'o', (byte)'n', (byte)'f', (byte)'i', (byte)'g', (byte)'u', (byte)'r', (byte)'e', (byte)'d', (byte)'.'};
	private static final byte[]  MCCMNCFFFF = {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
	private static final byte[]  USIM_AID = {(byte)0xA0, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x87, (byte)0x10, (byte)0x02, (byte)0xF2, (byte)0x30, (byte)0xFF, (byte)0x01, (byte)0x89, (byte)0x07, (byte)0x02, (byte)0x00, (byte)0x00};
	//-------------------DEBUG-------------------
	/*
	public static final byte IMEI = 0;
	public static final byte MCCMNC = 1;
	//public static final byte NIBBLESWAP = 2;
	//public static final byte NORMAL = 3;
	private static final byte[]  mcc_mnc = {(byte)'M', (byte)'C', (byte)'C', (byte)' ', (byte)'M', (byte)'N', (byte)'C', (byte)':'};
	//private static final byte[]  acc1 = {(byte)'A', (byte)'C', (byte)'C', (byte)':', (byte)' '};
	private static final byte[]  imsi1 = {(byte)'I', (byte)'M', (byte)'S', (byte)'I', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'b', (byte)'e', (byte)' ', (byte)'s', (byte)'w', (byte)'i', (byte)'t', (byte)'c', (byte)'h', (byte)'e', (byte)'d', (byte)':', (byte)' '};
	private static final byte[]  imsi2 = {(byte)'C', (byte)'u', (byte)'r', (byte)'r', (byte)'e', (byte)'n', (byte)'t', (byte)' ', (byte)'I', (byte)'M', (byte)'S', (byte)'I', (byte)':', (byte)' '};
	//private static final byte[]  debugON = {(byte)'D', (byte)'e', (byte)'b', (byte)'u', (byte)'g', (byte)' ', (byte)'O', (byte)'N'};
	//private static final byte[]  debugOFF = {(byte)'D', (byte)'e', (byte)'b', (byte)'u', (byte)'g', (byte)' ', (byte)'O', (byte)'F', (byte)'F'};
	//private static final byte[]  ddd = {(byte)'1', (byte)'2', (byte)'3', (byte)'4', (byte)'5', (byte)'6', (byte)'7', (byte)'8', (byte)'9'};
	private boolean Debug = true;
	//private byte[] byteArray2 = JCSystem.makeTransientByteArray((short) 160, JCSystem.CLEAR_ON_RESET);//250
	 */
	//-------------------NVM-------------------END
	private ToolkitRegistry mReg;
	private FileView fvMF;
	private FileView fvADF;
	AID AidADF;
	private byte[] byteArray1 = JCSystem.makeTransientByteArray((short) 160, JCSystem.CLEAR_ON_RESET);//250
	private byte[] MCCid = JCSystem.makeTransientByteArray((short) 1, JCSystem.CLEAR_ON_RESET);
	private byte[] Cntr = JCSystem.makeTransientByteArray((short) 1, JCSystem.CLEAR_ON_RESET);
	private byte[] MCC_Type = JCSystem.makeTransientByteArray((short) 1, JCSystem.CLEAR_ON_RESET);
	private byte[] IMSInum = JCSystem.makeTransientByteArray((short) 25, JCSystem.CLEAR_ON_RESET);
	private byte[] IMSIStatus = JCSystem.makeTransientByteArray((short) 3, JCSystem.CLEAR_ON_RESET);
	private byte[] byteArray2 = JCSystem.makeTransientByteArray((short) 100, JCSystem.CLEAR_ON_RESET);//250
	public static final byte NORMAL = 3;
	public static final byte NIBBLESWAP = 2;

	private byte[] PermLOCI = {(byte) 0x00,(byte) 0x00};
	private byte[] itemId = {(byte) 0x00,(byte) 0x00,(byte) 0x00, (byte) 0x00};
	private byte IMSI_Ind = 0x55;
	private byte MI_mode = 0x01;//1 = Auto, 2 = manual
	//private boolean first=true;

	public Telna_MultiIMSI(byte bArray[], short bOffset, byte bLength) {
		short workOffset = bOffset;
		// Skip the instance AID
		workOffset += (short)(bArray[workOffset] + 1);
		// Skip the application privileges
		workOffset += (short)(bArray[workOffset] + 1);
		// Get the application parameters length
		byteArray1[0] = (byte)(bArray[workOffset++] & 0xff);
		Util.arrayCopyNonAtomic(bArray, (short)workOffset, byteArray1, (short)1, (short)byteArray1[0]);//USIM AID
		register();

		// get the GSM application reference
		fvMF = UICCSystem.getTheUICCView(JCSystem.CLEAR_ON_RESET);
		// Init the ADF File View
		if(byteArray1[0]==0){//no AID in C9 Params
			AidADF = new AID(USIM_AID, (short) 0, (byte) USIM_AID.length);
		}else{//AID in C9 params
			AidADF = new AID(byteArray1, (short) 1, (byte) byteArray1[0]);
		}		
		fvADF = UICCSystem.getTheFileView(AidADF, JCSystem.CLEAR_ON_RESET);

		// register to the SIM Toolkit Framework
		mReg = ToolkitRegistrySystem.getEntry();
		mReg.setEvent(EVENT_FIRST_COMMAND_AFTER_ATR);
		mReg.setEvent(EVENT_PROFILE_DOWNLOAD);
		//mReg.setEvent(EVENT_EVENT_DOWNLOAD_LOCATION_STATUS);	
		itemId[0] = mReg.initMenuEntry(Country,(short)0,(short)Country.length, (byte)0x00, false, (byte)0x00, (byte)0x00);//Country
		itemId[1] = mReg.initMenuEntry(Mode,(short)0,(short)Mode.length, (byte)0x00, false, (byte)0x00, (byte)0x00);//Mode	
		itemId[2] = mReg.initMenuEntry(ICCID,(short)0,(short)ICCID.length, (byte)0x00, false, (byte)0x00, (byte)0x00);//ICCID	
	}

	public static void install(byte bArray[], short bOffset, byte bLength) {
		new Telna_MultiIMSI(bArray, bOffset, bLength);
	}

	/**
	 * Method called by the Javacard Framework
	 **/ 
	public Shareable getShareableInterfaceObject(AID clientAID, byte parameter)
	{
		if (clientAID == null) // It's the system invoking
			return((Shareable)this);
		return(null);
	}

	public void process(APDU apdu) throws ISOException {
		if (selectingApplet()) {
			return;
		}		
	}

	public void processToolkit(short event) throws ToolkitException {
		short i=0;
		ProactiveHandler proHdlr;
		ProactiveResponseHandler rspHdlr;

		switch(event) 
		{
		case EVENT_FIRST_COMMAND_AFTER_ATR:
			//**************GET ALL THE FILE SIZES****************
			fvMF.select((short) 0x3F00);//SIMView.FID_MF
			fvMF.select((short) DF_WorkzMI);
			i = fvMF.select((short)EF_MI_Multi_IMSI, byteArray1, (short)0, (short)50);
			i = getSimpleTLVTagOffset((byte)0x82, byteArray1, (short)2, (short)(i-2));
			if(IMSI_FILE_COUNT != byteArray1[(short)(i+6)]){
				IMSI_FILE_COUNT = (short) (byteArray1[(short)(i+6)]&0xFF);//Num of Records
			}			

			i = fvMF.select((short)EF_MI_Multi_SMSC, byteArray1, (short)0, (short)50);
			i = getSimpleTLVTagOffset((byte)0x82, byteArray1, (short)2, (short)(i-2));
			if(SMSP_SIZE != byteArray1[(short)(i+5)]){
				SMSP_SIZE = (short) (byteArray1[(short)(i+5)]&0xFF);//Record Size
			}
			
			i = fvMF.select((short)EF_MI_PPLMNWACT, byteArray1, (short)0, (short)50);
			i = getSimpleTLVTagOffset((byte)0x82, byteArray1, (short)2, (short)(i-2));
			if(PPLMN_Size != byteArray1[(short)(i+5)]){
				PPLMN_Size = (short) (byteArray1[(short)(i+5)]&0xFF);//Record Size
			}
			
			i = fvMF.select((short)EF_MI_GID1, byteArray1, (short)0, (short)50);
			i = getSimpleTLVTagOffset((byte)0x82, byteArray1, (short)2, (short)(i-2));
			if(GID1_Size != byteArray1[(short)(i+5)]){
				GID1_Size = (short) (byteArray1[(short)(i+5)]&0xFF);//Record Size
			}
			
			i = fvMF.select((short)EF_MI_GID2, byteArray1, (short)0, (short)50);
			i = getSimpleTLVTagOffset((byte)0x82, byteArray1, (short)2, (short)(i-2));
			if(GID2_Size != byteArray1[(short)(i+5)]){
				GID2_Size = (short) (byteArray1[(short)(i+5)]&0xFF);//Record Size
			}

			i = fvMF.select((short)EF_MI_COUNTRY_LIST, byteArray1, (short)0, (short)50);//EF_Account_Name
			i = getSimpleTLVTagOffset((byte)0x82, byteArray1, (short)2, (short)(i-2));
			if(Country_SIZE != byteArray1[(short)(i+5)]){
				Country_SIZE = (short) (byteArray1[(short)(i+5)]&0xFF);//Record Size
			}
			if(Country_COUNT != byteArray1[(short)(i+6)]){
				Country_COUNT = (short) (byteArray1[(short)(i+6)]&0xFF);//Record Count
			}

			//*****************************************************	
			ReadIMSImapTable();
			break;
		case EVENT_PROFILE_DOWNLOAD:
			// get the handler references
			proHdlr = ProactiveHandlerSystem.getTheHandler();//command 
			Cntr[0]=0;
			//If the ME doesn't support Poll Interval, Refresh or PLI then DT Change Handset
			for(short j=0; j < (short)PROFILE.length ; j++)
			{
				if(!uicc.toolkit.TerminalProfile.check(PROFILE[j]))
				{
					print((byte)0x81, ChangeHandset, (short)0, (short)ChangeHandset.length);
					//STK Disabled and Auto Roam disabled
					return;
				}
			}

			fvMF.select((short) EF_MI_IMSI_Status);//EF_IMSIStatus
			fvMF.readBinary((short)0, IMSIStatus, (short)0,(short)3);
			if(IMSIStatus[2]==1){//Status part-1
				if(IMSIStatus[0]!=(byte)0xFF && MI_mode!=IMSIStatus[0]){
					MI_mode=IMSIStatus[0];
					/*if(MI_mode==2){
						if (mReg.getPollInterval() != POLL_NO_DURATION) {
							mReg.requestPollInterval(POLL_NO_DURATION);
						}
					}*/
				}
			}

			if(MI_mode!=2){
				//if (!mReg.isEventSet(EVENT_STATUS_COMMAND))
				mReg.requestPollInterval(POLL_SYSTEM_DURATION);//request poll interval
				mReg.setEvent(EVENT_EVENT_DOWNLOAD_LOCATION_STATUS);
			}else if(DT_AutoDisabled_flag){
				print((byte)0x01, DT_AutoDisabled, (short)0, (short)DT_AutoDisabled.length);
			}

			if(IMSIStatus[2]==1){//Status part-2
				IMSIStatus[2]=0;
				fvMF.updateBinary((short)2,IMSIStatus,(short)2,(short)1);

				if(IMSI_Ind!=IMSIStatus[1]){
					ActionSwitchingCondition(proHdlr,(byte)IMSIStatus[1]);
				}
			}
			break;
		case EVENT_EVENT_DOWNLOAD_LOCATION_STATUS:
		case EVENT_STATUS_COMMAND:
			// get the handler references
			proHdlr = ProactiveHandlerSystem.getTheHandler();//command 
			fvMF.select((short) EF_MI_IMSI_Status);//EF_IMSIStatus
			fvMF.readBinary((short)0, IMSIStatus, (short)0,(short)3);
			if(IMSIStatus[2]==1){//Status part-1
				if(IMSIStatus[0]!=(byte)0xFF && MI_mode!=IMSIStatus[0]) MI_mode=IMSIStatus[0];
				IMSIStatus[2]=0;
				fvMF.updateBinary((short)2,IMSIStatus,(short)2,(short)1);

				ReadIMSImapTable();

				if(IMSI_Ind!=IMSIStatus[1]){
					ActionSwitchingCondition(proHdlr,(byte)IMSIStatus[1]);
				}
			}else{
				proHdlr.init(PRO_CMD_PROVIDE_LOCAL_INFORMATION, (byte)0x00, DEV_ID_TERMINAL);
				if(proHdlr.send() <= 0x14){//RES_CMD_PERF_USSD_TRANSAC_TERM = 0x14
					rspHdlr = ProactiveResponseHandlerSystem.getTheHandler();
					// look for location information element
					if (rspHdlr.findTLV((byte)TAG_LOCATION_INFORMATION, (byte)0x01) != TLV_NOT_FOUND) {
						if (rspHdlr.getValueLength() > 1) {
							rspHdlr.copyValue((short)0, byteArray1, (short)0, (short)3);//get MCC-MNC
							if(byteArray1[0]!=PermLOCI[0] && (byteArray1[1]&0x0F)!=(PermLOCI[1]&0x0F)){
								Util.arrayCopyNonAtomic(byteArray1, (short)0, PermLOCI, (short)0, (short)2);
								MI_mode=1;
							}
							if(MI_mode!=2){
								//Check if the IMSI is not active for 2 mins then
								MCCid[0] = MCCidentification(proHdlr, byteArray1);
								if(MCC_Type[0]!=5){//good case
									ActionSwitchingCondition(proHdlr,(byte)MCCid[0]);
								}
							}
						}
					}				
				}
			}
			break;
		case EVENT_MENU_SELECTION:
			// get the handler references
			proHdlr = ProactiveHandlerSystem.getTheHandler();//command 
			rspHdlr = ProactiveResponseHandlerSystem.getTheHandler();//response
			ProcessEventMenuSelection(proHdlr,rspHdlr);
			break;
		}
	}

	private void ProcessEventMenuSelection(ProactiveHandler proHdlr, ProactiveResponseHandler rspHdlr) {
		EnvelopeHandler envHdlr = EnvelopeHandlerSystem.getTheHandler();
		byte selection = envHdlr.getItemIdentifier();
		short phase=0,result=0,i=1,j=0,k=0,len=0,div=0,rem=0,l=0;

		if(selection == itemId[0])
			phase=1;//Country
		else if(selection == itemId[1])
			phase=2;//Mode
		else if(selection == itemId[2])
			phase=3;//ICCID

		do{
			switch(phase){
			case 1://Country
				//phase=100;
				fvMF.select((short) DF_WorkzMI);
				fvMF.select((short) EF_MI_COUNTRY_LIST);//EF_Country
				div=(short) ((Country_COUNT/Country_Display)*Country_Display);
				rem=(short) (Country_COUNT%Country_Display);
				do{

					for(l=0;l<3500;l++);//Delay introduced

					proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_TERMINAL);
					proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER | TAG_SET_CR), Country,(short)0,(short)Country.length);
					if(i>1){//back
						proHdlr.appendTLV((byte)(TAG_ITEM ),(byte)0x21, back,(short)0,(short)back.length);
					}
					for(j=i;j<(short)(i+(i==(short)(div+1)?rem:Country_Display));j++){
						fvMF.readRecord((short)j,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)0,(short)Country_SIZE);//VTG - HOME IMSI
						//Util.arrayCopyNonAtomic(ttt, (short)((j-1)*20), byteArray1, (short)0, (short)20);//Reads array from Applet
						IMSInum[(short)(j-i)]=byteArray1[0];
						for(k=1;byteArray1[k]!=(byte)0xFF && k<Country_SIZE;k++);
						proHdlr.appendTLV((byte)(TAG_ITEM | TAG_SET_CR),(byte)(j-i+1), byteArray1,(short)1,(short)(k-1));
					}
					if(i<(short)(Country_COUNT-(i==(short)(div+1)?rem:Country_Display))){//next
						proHdlr.appendTLV((byte)(TAG_ITEM ),(byte)0x22, next,(short)0,(short)next.length);
					}
					result = proHdlr.send();
					if(result == RES_CMD_PERF){
						k = (short)rspHdlr.getItemIdentifier();
						if(k==(byte)0x22){
							//if(i!=(223/10))
							i+=Country_Display;
						}
						else if(k==(byte)0x21){
							i-=Country_Display;
						}
						else{
							phase=100;

							if(MI_mode!=1){
								MI_mode=1;
								if (!mReg.isEventSet(EVENT_STATUS_COMMAND))
									mReg.requestPollInterval(POLL_SYSTEM_DURATION);//request poll interval
							}
							//*********** SET 12 bytes FPLMN content to FF...FF ******** 
							//SelectEF(SIMView.FID_DF_GSM, (short)0x6F7B);//FPLMN
							fvADF.select((short) 0x6F7B);//FPLMN under ADF_USIM
							Util.arrayFillNonAtomic(byteArray1, (short)0, (short)12, (byte)0xFF);
							fvADF.updateBinary((short)0,byteArray1,(short)0,(short)12);

							ActionSwitchingCondition(proHdlr,(byte)IMSInum[(short)(k-1)]);
						}
					}
					else phase=100;
				}while(phase!=100);
				break;
			case 2://Mode
				phase=100;
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_TERMINAL);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER | TAG_SET_CR), Mode,(short)0,(short)Mode.length);
				proHdlr.appendTLV((byte) (TAG_ITEM | TAG_SET_CR),(byte)1, Auto,(short)(MI_mode==1?0:1),(short)(Auto.length-(MI_mode==1?0:1)));
				proHdlr.appendTLV((byte) (TAG_ITEM | TAG_SET_CR),(byte)2, Manual,(short)(MI_mode==2?0:1),(short)(Manual.length-(MI_mode==2?0:1)));
				result = proHdlr.send();
				if(result == RES_CMD_PERF){
					phase = (short)(20+rspHdlr.getItemIdentifier());
				}
				break;
			case 21://Automatic
				phase=100;
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_TERMINAL);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER ), Auto,(short)1,(short)(Auto.length-1));
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)1, on,(short)(MI_mode==1?0:1),(short)(on.length-(MI_mode==1?0:1)));
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)2, off,(short)(MI_mode==2?0:1),(short)(off.length-(MI_mode==2?0:1)));
				result = proHdlr.send();
				if(result == RES_CMD_PERF){
					phase = (short)(30+rspHdlr.getItemIdentifier());
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase = 2;
				}
				break;
			case 31://Automatic ON
				phase=100;
				if(MI_mode!=1){
					MI_mode=1;
					/*if (!mReg.isEventSet(EVENT_STATUS_COMMAND))
						mReg.requestPollInterval(POLL_SYSTEM_DURATION);//request poll interval
					 */
				}		

				proHdlr.init(PRO_CMD_PROVIDE_LOCAL_INFORMATION, (byte)0x00, DEV_ID_TERMINAL);
				if(proHdlr.send() <= 0x14){//RES_CMD_PERF_USSD_TRANSAC_TERM = 0x14
					rspHdlr = ProactiveResponseHandlerSystem.getTheHandler();
					// look for location information element
					if (rspHdlr.findTLV((byte)TAG_LOCATION_INFORMATION, (byte)0x01) != TLV_NOT_FOUND) {
						if (rspHdlr.getValueLength() > 1) {
							rspHdlr.copyValue((short)0, byteArray1, (short)0, (short)3);//get MCC-MNC
							if(byteArray1[0]!=PermLOCI[0] && (byteArray1[1]&0x0F)!=(PermLOCI[1]&0x0F)){
								Util.arrayCopyNonAtomic(byteArray1, (short)0, PermLOCI, (short)0, (short)2);
								MI_mode=1;
							}
						}
					}
					if(MI_mode!=2){
						//Check if the IMSI is not active for 2 mins then
						MCCid[0] = MCCidentification(proHdlr, byteArray1);
						if(MCC_Type[0]!=5){//good case
							ActionSwitchingCondition(proHdlr,(byte)MCCid[0]);
						}
					}
				}			
				break;
			case 32:////Automatic OFF
				phase=100;
				if(MI_mode!=2){
					MI_mode=2;
					/*if (mReg.getPollInterval() != POLL_NO_DURATION) {
						mReg.requestPollInterval(POLL_NO_DURATION);
					}*/
				}
				break;
			case 22://Manual
				phase=100;
				fvADF.select((short) 0x6F07);//EF_IMSI under ADF_USIM
				fvADF.readBinary((short)0, byteArray1, (short)100,(short)9);
				/*fvMF.select((short) DF_WorkzMI);
				fvMF.select((short) EF_MI_Multi_IMSI);//EF_MI_Multi_IMSI
				for(j=1;j<=(short)IMSI_FILE_COUNT;j++){
					fvMF.readRecord((short)j,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)0,(short)9);//IMSI
					if(Util.arrayCompare(byteArray1, (short)0, byteArray2, (short)0, (short) 9)==0){
						if(IMSI_Ind != (byte) j){
							IMSI_Ind = (byte) j;
						}						
						break;
					}
				}*/
				fvMF.select((short) DF_WorkzMI);
				fvMF.select((short) EF_MI_IMSI_NUM);//EF_MI_Multi_IMSI
				fvMF.readBinary((short)0, byteArray2, (short)0,(short)IMSI_FILE_COUNT);//AJITAV - 260B size set as 20
				fvMF.select((short) EF_MI_Multi_IMSI);//EF_MI_Multi_IMSI
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_TERMINAL);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER ), Manual,(short)1,(short)(Manual.length-1));
				for(j=1,i=1;j<=(short)IMSI_FILE_COUNT;j++){
					fvMF.readRecord((short)j,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)0,(short)9);//IMSI
					if(Util.arrayCompare(byteArray1, (short)0, IMSI_FF, (short)0, (short) 9)!=0){
						IMSInum[i]=(byte) j;
						if(Util.arrayCompare(byteArray1, (short)0, byteArray1, (short)100, (short) 9)==0){
							if(IMSI_Ind != (byte) j){
								IMSI_Ind = (byte) j;
							}
						}
						k=(short)(j==IMSI_Ind?0:1);//Length Adjustment in case of highlighted *
						Util.arrayCopyNonAtomic(MIMSI, (short)k, byteArray1, (short)0, (short)(MIMSI.length-k));
						//byteArray1[(short)(MIMSI.length-k)]=(byte)(j+48);
						//proHdlr.appendTLV((byte)(TAG_ITEM ),(byte)j, byteArray1,(short)0,(short)(MIMSI.length-k+1));
						len = Byte_to_ASCIIdigits(byteArray2[(short)(j-1)], byteArray1, (short)(MIMSI.length-k));
						proHdlr.appendTLV((byte)(TAG_ITEM ),(byte)j, byteArray1,(short)0,(short)len);
						i++;
					}
				}
				result = proHdlr.send();
				if(result == RES_CMD_PERF){
					if(MI_mode!=2){
						MI_mode=2;
						/*if (mReg.getPollInterval() != POLL_NO_DURATION) {
							mReg.requestPollInterval(POLL_NO_DURATION);
						}*/
					}
					ActionSwitchingCondition(proHdlr,IMSInum[rspHdlr.getItemIdentifier()]);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase = 2;
				}
				break;
			case 3://ICCID
				phase=100;
				fvMF.select((short) 0x3F00);//EF_ICCID
				fvMF.select((short) 0x2FE2);//EF_ICCID
				fvMF.readBinary((short)0, byteArray1, (short)0,(short)10);
				i = Util.arrayCopyNonAtomic(ICCID,(short)0,byteArray2,(short)0,(short) ICCID.length);
				byteArray2[i]=' ';
				i++;
				i += NibbleSwapToAscii(byteArray1,(short)0,(short)10,byteArray2,(short)i,NIBBLESWAP);
				for(k=0;byteArray2[k]!=(byte)'F' && k<25;k++);
				print((byte)0x01, byteArray2,(short)0, (short)k);
				break;
			}

		}while(phase!=100);
	}

	private boolean ActionSwitchingCondition(ProactiveHandler proHdlr, byte mccId){
		try{
			short acc=0,i=0;
			//byteArray1[0]=mccId;//-----------DEBUG
			//print((byte)0x81, byteArray1, (short)0, (short)1);//-----------DEBUG
			//SelectEF(SIMView.FID_DF_GSM, (short) 0x6F07);//EF_IMSI
			fvADF.select((short) 0x6F07);//EF_IMSI under ADF_USIM
			fvADF.readBinary((short)0, byteArray1, (short)0,(short)9);

			SelectEF(DF_WorkzMI, EF_MI_Multi_IMSI);//EF_MULTI_IMSI
			fvMF.readRecord((short)mccId,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)9,(short)9);//VTG - HOME IMSI
			//fvMF.select((short) EF_MI_Multi_SMSC);
			//fvMF.readRecord((short)mccId,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,MultiSMSC,(short)0,(short)SMSP_SIZE);//Multi-SMSC

			if(Util.arrayCompare(byteArray1, (short)9, IMSI_FF, (short)0, (short)9)==0)
				return false;
			if(Util.arrayCompare(byteArray1, (short)0, byteArray1, (short)9, (short)9)!=0){//If the current IMSI is not equal to the selected IMSI
				/*if(Debug){
				short len=0;
				len = Util.arrayCopyNonAtomic(imsi1,(short)0,byteArray2,(short)len,(short) imsi1.length);
				len += NibbleSwapToAscii(MultiIMSI,(short)0,(short)9,byteArray2,(short)len,NIBBLESWAP);
				print((byte)0x01, byteArray2,(short)0, (short)len);
			}*/
				//SelectEF(SIMView.FID_DF_GSM, (short) 0x6F07);//EF_IMSI
				fvADF.select((short) 0x6F07);//EF_IMSI under ADF_USIM
				fvADF.updateBinary((short)0,byteArray1,(short)9,(short)9);
				if(byteArray1[9]<=8){//ACC Update logic
					i=(short)((byteArray1[byteArray1[9]]>>4)&0x0F);
					if(i<10){//Valid digit 0 to 9
						acc=(short) (1<<i);
						Util.setShort(byteArray1, (short)0, acc);
						fvADF.select((short) 0x6F78);
						fvADF.updateBinary((short)0,byteArray1,(short)0,(short)2);
						/*if(Debug){
						short len=0;
						len = Util.arrayCopyNonAtomic(acc1,(short)0,byteArray2,(short)len,(short) acc1.length);
						len += NibbleSwapToAscii(byteArray1,(short)0,(short)2,byteArray2,(short)len,NORMAL);
						print((byte)0x01, byteArray2,(short)0, (short)len);
					}*/
					}
				}
				//SelectEF(SIMView.FID_DF_TELECOM, (short) 0x6F42);//EF_SMSP
				SelectEF(DF_WorkzMI, EF_MI_Multi_SMSC);//EF_MI_Multi_SMSC
				fvMF.readRecord((short)mccId,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)0,(short)SMSP_SIZE);//Multi-SMSC
				fvADF.select((short) 0x6F42);//EF_SMSP under ADF_USIM
				fvADF.updateRecord((short)1, UICCConstants.REC_ACC_MODE_ABSOLUTE ,(short)0,byteArray1,(short)0,(short)SMSP_SIZE); //Copy SMSC to EF_SMSP
				
				SelectEF(DF_WorkzMI, EF_MI_PPLMNWACT);//EF_MI_PPLMNWACT
				fvMF.readRecord((short)mccId,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)0,(short)PPLMN_Size);//Multi-SMSC
				fvADF.select((short) 0x6F60);//EF_PLMNwAct under ADF_USIM
				fvADF.updateBinary((short)0,byteArray1,(short)0,(short)PPLMN_Size); //Copy PPLMN to EF_PLMNwAct
				fvADF.select((short) 0x6F61);//EF_OPLMNwAct under ADF_USIM
				fvADF.updateBinary((short)0,byteArray1,(short)0,(short)PPLMN_Size); //Copy PPLMN to EF_OPLMNwAct
				fvADF.select((short) 0x6F62);//EF_HPLMNwAct under ADF_USIM
				fvADF.updateBinary((short)0,byteArray1,(short)0,(short)PPLMN_Size); //Copy PPLMN to EF_HPLMNwAct
				
				for(i=0;i<(short)(PPLMN_Size/5);i++){
					Util.arrayCopyNonAtomic(byteArray1, (short)(i*5), byteArray2, (short)(i*3), (short)3);
				}
				try{
					SelectEF((short)0x7F20,(short)0x6F30);//EF_MI_PPLMNWACT
					fvADF.updateBinary((short)0,byteArray2,(short)0,(short)(PPLMN_Size*(short)(3/5))); //Copy PPLMN to PLMNSEL
				}catch(Exception e){}
				
				SelectEF(DF_WorkzMI, EF_MI_GID1);//EF_MI_Multi_SMSC
				fvMF.readRecord((short)mccId,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)0,(short)GID1_Size);//Multi-GID1
				fvADF.select((short) 0x6F3E);//EF_GID1 under ADF_USIM
				fvADF.updateBinary((short)0,byteArray1,(short)0,(short)GID1_Size); //Copy GID1 to EF_GID1
				
				SelectEF(DF_WorkzMI, EF_MI_GID2);//EF_MI_Multi_SMSC
				fvMF.readRecord((short)mccId,UICCConstants.REC_ACC_MODE_ABSOLUTE,(short)0,byteArray1,(short)0,(short)GID2_Size);//Multi-GID2
				fvADF.select((short) 0x6F3F);//EF_GID2 under ADF_USIM
				fvADF.updateBinary((short)0,byteArray1,(short)0,(short)GID2_Size); //Copy GID2 to EF_GID2
				//LastMCCInd = mccId;
				//print((byte)0x81, MultiSMSC,(short)0,(short)15);//-----------DEBUG
				try{
					//*********** SET 12 bytes FPLMN content to FF...FF ******** 
					//SelectEF(SIMView.FID_DF_GSM, (short)0x6F7B);//FPLMN
					fvADF.select((short) 0x6F7B);//EF_FPLMN under ADF_USIM
					Util.arrayFillNonAtomic(byteArray1, (short)0, (short)12, (byte)0xFF);
					fvADF.updateBinary((short)0,byteArray1,(short)0,(short)12);
					byteArray1[0]=0x01;//Initializing byte as 01 for the last byte Loci status
					fvADF.select((short) 0x6F7E);//EF_LOCI (6F7E)
					fvADF.updateBinary((short)10,byteArray1,(short)0,(short)1);
					fvADF.select((short) 0x6F73);//EF_PSLOCI (6F73)
					fvADF.updateBinary((short)13,byteArray1,(short)0,(short)1);			
					fvADF.select((short) 0x6FE3);//EPSLOCI (6FE3)
					fvADF.updateBinary((short)17,byteArray1,(short)0,(short)1);
				}catch(Exception e){}
				IMSI_Ind=mccId;//IMSI_Ind is used for highlighting the IMSI used in the STK

				if(IMSIStatus[1]!=IMSI_Ind){
					IMSIStatus[1]=IMSI_Ind;
					fvMF.select((short) 0x3F00);//MF
					fvMF.select((short) DF_WorkzMI);//DF_9009
					fvMF.select((short) EF_MI_IMSI_Status);//EF_IMSIStatus
					fvMF.updateBinary((short)1,IMSIStatus,(short)1,(short)1);
				}
				fvMF.select((short) EF_MI_MNC_Len);//EF_MNC_Length
				fvMF.readBinary((short)(IMSI_Ind-1), byteArray2, (short)0,(short)1);
				if(byteArray2[0]==2 || byteArray2[0]==3){
					fvADF.select((short) 0x6FAD);//EF_AD
					fvADF.updateBinary((short)3,byteArray2,(short)0,(short)1);					
				}
				
				try{
					fvMF.select((short) EF_MI_Multi_Settings);//EF_MNC_Length
					fvMF.readBinary((short)0, byteArray2, (short)0,(short)1);
					if(byteArray2[0]==(byte)0xFF){
						byteArray2[0]=0x04;//04-SIM Reset, 00-SIM Init and Full-File change notification	
					}
					if(byteArray2[5]==(byte)0x00){
						if(DT_AutoDisabled_flag) DT_AutoDisabled_flag = false;
					}else{
						if(!DT_AutoDisabled_flag) DT_AutoDisabled_flag = true;
					}
				}catch(Exception e){
					byteArray2[0]=0x04;//04-SIM Reset, 00-SIM Init and Full-File change notification	
				}

				proHdlr.init(PRO_CMD_REFRESH, (byte)byteArray2[0], (byte)0x82);//04-SIM Reset, 00-SIM Init and Full-File change notification
				if(proHdlr.send()>RES_CMD_PERF_WITH_MODIFICATION)
					print((byte)0x81, DT_RestartHandset, (short)0, (short)DT_RestartHandset.length);
			}
			else{
				/*if(Debug){
				short len=0;
				len = Util.arrayCopyNonAtomic(imsi2,(short)0,byteArray2,(short)len,(short) imsi2.length);
				len += NibbleSwapToAscii(MultiIMSI,(short)0,(short)9,byteArray2,(short)len,NIBBLESWAP);
				print((byte)0x01, byteArray2,(short)0, (short)len);
			}*/
				return false;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}

	/*private boolean LocationUpdated(ProactiveHandler proHdlr){
		SelectEF(SIMView.FID_DF_GSM, (short) 0x6F7E);//EF_LOCI
		mFv.readBinary((short)0, byteArray1, (short)9,(short)11);
		if(byteArray1[(short)(9+10)]==0x00)//If location update is successful
			return true;
		return false;
	}*/


	private byte MCCidentification(ProactiveHandler proHdlr, byte[] bytearray11){
		short i=0;
		MCC_Type[0]=0;
		//SelectEF(SIMView.FID_DF_GSM, (short) 0x6F7E);//EF_LOCI
		//mFv.readBinary((short)4, byteArray1, (short)0,(short)3);//get MCC-MNC


		/*if(Debug){
			short len=0;
			i = Util.arrayCopyNonAtomic(mcc_mnc,(short)0,byteArray2,(short)i,(short) mcc_mnc.length);
			len = (short) (NibbleSwapToAscii(byteArray1,(short)0,(short)3,byteArray2,(short)i,MCCMNC)+i);
			if(byteArray2[(short)(i+3)]!='F'){
				byteArray2[len]=byteArray2[(short)(i+3)];
				byteArray2[(short)(i+3)]='-';
				len++;
			}
			byteArray2[(short)(i+3)]='-';
			print((byte)0x01, byteArray2,(short)0, (short)len);
		}*/

		for(i=0;i<MCC_List_SIZE;i+=4){

			/*short len=0;
					Util.setShort(byteArray2, (short)10,i);
					len = NibbleSwapToAscii(byteArray2,(short)10,(short)2,byteArray2,(short)len,NORMAL);
					print((byte)0x01, byteArray2,(short)0, (short)len);*/

			if(Util.arrayCompare(MCC_List, (short)i, MCCMNCFFFF, (short)0, (short)4)!=0 && MCC_List[(short)(i+3)]!=(byte)0xFF){//If MCC-MNC-Index is not FF..FF
				if(Util.arrayCompare(MCC_List, (short)i, bytearray11, (short)0, (short)3)==0){//MCC-MNC Present
					MCC_Type[0]=1;//MCC-MNC
					return MCC_List[(short)(i+3)];//return index
				}
				//else if(Util.arrayCompare(MCC_List, (short)i, byteArray1, (short)0, (short)2)==0 && MCC_List[(short)(i+2)]==(byte)0xFF){//MCC Present
				else if(MCC_List[i]==bytearray11[0] && (MCC_List[(short)(i+1)]&0x0F)==(bytearray11[1]&0x0F) && MCC_List[(short)(i+2)]==(byte)0xFF){//MCC Present
					MCC_Type[0]=2;//MCC Only
					return MCC_List[(short)(i+3)];//return index
				}
				else if(Util.arrayCompare(MCC_List, (short)i, MCCMNCFFFF, (short)0, (short)2)==0 && bytearray11[2]==MCC_List[(short)(i+2)])//MNC present
				{
					MCC_Type[0]=3;//MNC Only
					return MCC_List[(short)(i+3)];//return index
				}
				else if(Util.arrayCompare(MCC_List, (short)i, MCCMNCFFFF, (short)0, (short)3)==0){
					MCC_Type[0]=4;//No MCC-MNC preference for Index
					return MCC_List[(short)(i+3)];//return index
				}
			}
		}//end For

		MCC_Type[0]=5;//No MCC-MNC-Index
		return (byte)0xFF;
	}

	short NibbleSwapToAscii(byte[] srcArr, short srcOff, short srcLen, byte[] destArr, short destOff, byte ct){
		short j=0;
		short sw = (short) (ct==NORMAL?0:1);
		for(short i=0;i<srcLen;i++){
			sw=(short) (1-sw);
			destArr[(short)(destOff+j+sw)]= toDigits((byte) (srcArr[(short)(srcOff+i)]&0x0F), ct);
			sw=(short) (1-sw);
			destArr[(short)(destOff+j+sw)]= toDigits((byte) ((srcArr[(short)(srcOff+i)]&0xF0)>>4), ct);
			j+=2;
		}
		return j;
	}

	byte toDigits(short s, byte ct){
		if(ct == NORMAL || ct == NIBBLESWAP){
			if(s>9) return (byte) (s+55);//ABCDEF
		}
		if(s<10) return (byte) (s+48);//0123456789
		else return 0x20;
	}

	public static short Byte_to_ASCIIdigits(short bVal,byte[] bArray, short Offset)//input:97 output:"9" "7"
	{
		short reverseVal1 = 0;
		byte i = 0, j = 0;
		for(;bVal!=0;bVal=(short)(bVal/10),i++){
			reverseVal1 = (short)(reverseVal1*10 + (bVal%10));
		}
		for(;j<i;reverseVal1/=10,j++){
			bArray[(short)(j+Offset)] = (byte) ((reverseVal1%10) + '0');
		}
		return (short) (j+Offset);
	}
	public static byte getChar(byte x)
	{
		if(x>=0 && x<=9)
			return (byte) (x+48);
		else if(x>=0x0A && x<=0x0F)
			return (byte) (x+0x37);
		return 48;
	}

	private void SelectEF(short df, short fileID){
		fvMF.select((short) 0x3F00);//SIMView.FID_MF
		fvMF.select((short) df);
		fvMF.select((short) fileID);
	}

	private void ReadIMSImapTable()
	{
		short k=0,i=0,div=0,rem=0;
		boolean updated=false;
		div=(short) (MCC_List_SIZE/100);
		rem=(short) (MCC_List_SIZE%100);
		if(rem!=0) k=(short) (div+1);
		else k=div;

		//Read the MultiIMSI File and update the MCC_List
		//SelectEF(DF_WorkzMI,(short)EF_MI_PLMN_MAP);//EF_MULTI_IMSI
		fvMF.select((short) DF_WorkzMI);
		fvMF.select((short) EF_MI_PLMN_MAP);//EF_MULTI_IMSI
		for(i=0;i<k;i++){
			fvMF.readBinary((short)(i*100), byteArray1, (short)0,(short)((i==(short)(k-1))?rem:100));
			//print((byte)0x01,byteArray1,(short)0,(short)100);
			//print((byte)0x01,MCC_List,(short)(i*100),(short)((i==(short)(k-1))?rem:100));
			if(Util.arrayCompare(byteArray1, (short)0, MCC_List, (short)(i*100), (rem==0)?100:rem)!=0){
				updated=true;
			}
		}
		if(updated){
			//print((byte)0x01,AutoMode,(short)0,(short)1);
			fvMF.readBinary((short)0, MCC_List, (short)0,(short)MCC_List_SIZE);
		}

		//for(i=0;i<k;i++){//For Debug purposes
		//	print((byte)0x01,MCC_List,(short)(i*100),(short)((rem==0)?100:rem));				
		//}
	}

	public short getSimpleTLVTagOffset(byte Tag, byte[] arr, short off, short len){
		short i=off;
		try{
			for(;(arr[i]!=Tag) && (short)(i+arr[(short)(i+1)]+2)<len;i+=arr[(short)(i+1)]+2);                     
			if(arr[i]!=Tag){
				//Tag not found
				return 0;
			}else{
				//Tag found, return offset
				return i;
			}
		}catch(Exception e){}
		return 0;
	}

	public void print(byte CQ, byte[] arr,short off, short len){
		ProactiveHandler proHdlr = ProactiveHandlerSystem.getTheHandler();//command 
		proHdlr.initDisplayText((byte)CQ, (byte)0x04, arr, (short)off, (short)len);//01: clear after delay/HP, 81: user input/HP
		//proHdlr.init(PRO_CMD_SET_UP_IDLE_MODE_TEXT, (byte)0x00, (byte)0x82);
		//proHdlr.appendTLV((byte)(TAG_TEXT_STRING| TAG_SET_CR), (byte)0x04 ,arr,(short)off, (short)len);
		proHdlr.send();
	}
}
