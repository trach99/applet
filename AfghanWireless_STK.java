package AfghanWireless_STK;

import javacard.framework.ISO7816;
import javacard.framework.AID;
import javacard.framework.APDU;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Shareable;
import javacard.framework.Util;
import sim.toolkit.EnvelopeHandler;
import sim.toolkit.ProactiveHandler;
import sim.toolkit.ProactiveResponseHandler;
import sim.toolkit.ToolkitConstants;
import sim.toolkit.ToolkitException;
import sim.toolkit.ToolkitInterface;
import sim.toolkit.ToolkitRegistry;

public class AfghanWireless_STK extends javacard.framework.Applet implements
ToolkitInterface, ToolkitConstants {

	private static final byte[]  Menu = 
	{
		(byte)4, (byte)4, (byte)8, (byte)9, (byte)16, (byte)9,  
		//Menu
		(byte)0x4D, (byte)0x65, (byte)0x6E, (byte)0x75,
		//مینو
		(byte)0x80, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x48,
		//مینو
		(byte)0x80, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x48
	};

	private static final byte[]  My_AWCC = 
	{
		(byte)4, (byte)7, (byte)11, (byte)29, (byte)40, (byte)31,
		//My AWCC
		(byte)0x4D, (byte)0x79, (byte)0x20, (byte)0x41, (byte)0x57, (byte)0x43, (byte)0x43,
		//افغان بیسیم من
		(byte)0x80, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x41, (byte)0x06, (byte)0x3A, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x46, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x28, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x45, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0x46,
		//زما افغان بیسیم
		(byte)0x80, (byte)0x06, (byte)0x32, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0x27, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x41, (byte)0x06, (byte)0x3A, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x46, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x28, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x45
	};

	private static final byte[] Balance_Check = 
	{
		(byte)4, (byte)13, (byte)17, (byte)39, (byte)56, (byte)47,
		//Balance Check
		(byte)0x42, (byte)0x61, (byte)0x6C, (byte)0x61, (byte)0x6E, (byte)0x63, (byte)0x65, (byte)0x20, (byte)0x43, (byte)0x68, (byte)0x65, (byte)0x63, (byte)0x6B,
		//کنترول موجودی کریدت
		(byte)0x80, (byte)0x06, (byte)0xA9, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x44, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x2C, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0xA9, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0x2A,
		//د حساب دښتوتوالی کنترول
		(byte)0x80, (byte)0x06, (byte)0x2F, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x2D, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x28, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0x9A, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0xA9, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x44	
	};

	private static final byte[] Top_Up = 
	{
		(byte)4, (byte)6, (byte)10, (byte)25, (byte)35, (byte)29,
		//Top Up
		(byte)0x54, (byte)0x6F, (byte)0x70, (byte)0x20, (byte)0x55, (byte)0x70,
		//ازدیاد کریدت
		(byte)0x80, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x32, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x2F, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0xA9, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0x2A,
		//د کریدت زیاتول
		(byte)0x80, (byte)0x06, (byte)0x2F, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0xA9, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0x2A, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x32, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x44
	};

	private static final byte[] CRBT_Access = 
	{
		(byte)4, (byte)11, (byte)15, (byte)43, (byte)58, (byte)45,
		//CRBT Access
		(byte)0x43, (byte)0x52, (byte)0x42, (byte)0x54, (byte)0x20, (byte)0x41, (byte)0x63, (byte)0x63, (byte)0x65, (byte)0x73, (byte)0x73,
		//دسترسی به سی آر بی تی
		(byte)0x80, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x28, (byte)0x06, (byte)0x47, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x22, (byte)0x06, (byte)0x31, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x28, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0xCC,
		//سی آر بی تی ته لاس رسی
		(byte)0x80, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x22, (byte)0x06, (byte)0x31, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x28, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x47, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x33, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0xCC
	};

	private static final byte[] Help_Line = 
	{
		(byte)4, (byte)9, (byte)13, (byte)29, (byte)42, (byte)39, 
		//Help Line
		(byte)0x48, (byte)0x65, (byte)0x6C, (byte)0x70, (byte)0x20, (byte)0x4C, (byte)0x69, (byte)0x6E, (byte)0x65,
		//رهنمای مشتریان
		(byte)0x80, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x47, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0xCC, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0x34, (byte)0x06, (byte)0x2A, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x46,
		//د پیرودونکو لارښونه
		(byte)0x80, (byte)0x06, (byte)0x2F, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x7E, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x2F, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0xA9, (byte)0x06, (byte)0x48, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x9A, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x47
	};

	private static final byte[] Language = 
	{
		(byte)4, (byte)9, (byte)13, (byte)9, (byte)22, (byte)7,
		//Dari
		(byte)0x80, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x48,
		//Pashto
		(byte)0x80, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0xCC, (byte)0x06, (byte)0x46, (byte)0x06, (byte)0x48,
		//English
		(byte)0x45, (byte)0x6E, (byte)0x67, (byte)0x6C, (byte)0x69, (byte)0x73, (byte)0x68
	};

	// private static final byte[] BalanceRefill = {
	// 	(byte)4, (byte)14, (byte)18, (byte)13,
	// 	(byte)0x42, (byte)0x61, (byte)0x6c, (byte)0x61, (byte)0x6e, (byte)0x63, (byte)0x65, (byte)0x20, (byte)0x52, (byte)0x65, (byte)0x66, (byte)0x69, (byte)0x6c, (byte)0x6c,
	// 	(byte)0x81, (byte)0x0A, (byte)0x0C, (byte)0xB4, (byte)0xAD, (byte)0xC6, (byte)0x20, (byte)0xA7, (byte)0xC4, (byte)0xB1, (byte)0xB5, (byte)0xCA, (byte)0xAF};

	// private static final byte[] BalanceTransfer = {
	// 	(byte)4, (byte)16, (byte)20, (byte)15,
	// 	(byte)0x42, (byte)0x61, (byte)0x6c, (byte)0x61, (byte)0x6e, (byte)0x63, (byte)0x65, (byte)0x20, (byte)0x54, (byte)0x72, (byte)0x61, (byte)0x6e, (byte)0x73, (byte)0x66, (byte)0x65, (byte)0x72,
	// 	(byte)0x81, (byte)0x0C, (byte)0x0C, (byte)0xAA, (byte)0xAD, (byte)0xC8, (byte)0xCA, (byte)0xC4, (byte)0x20, (byte)0xA7, (byte)0xC4, (byte)0xB1, (byte)0xB5, (byte)0xCA, (byte)0xAF};

	// private static final byte[] VoiceMail = {
	// 	(byte)4, (byte)9, (byte)13, (byte)16,
	// 	(byte)0x56, (byte)0x6f, (byte)0x69, (byte)0x63, (byte)0x65, (byte)0x4d, (byte)0x61, (byte)0x69, (byte)0x6c,
	// 	(byte)0x81, (byte)0x0D, (byte)0x0C, (byte)0xA7, (byte)0xC4, (byte)0xA8, (byte)0xB1, (byte)0xCA, (byte)0xAF, (byte)0x20, (byte)0xA7, (byte)0xC4, (byte)0xB5, (byte)0xC8, (byte)0xAA, (byte)0xC9};

	// private static final byte[] RingBackTone = {
	// 	(byte)4, (byte)14, (byte)18, (byte)15,
	// 	(byte)0x52, (byte)0x69, (byte)0x6e, (byte)0x67, (byte)0x20, (byte)0x42, (byte)0x61, (byte)0x63, (byte)0x6b, (byte)0x20, (byte)0x54, (byte)0x6f, (byte)0x6e, (byte)0x65,
	// 	(byte)0x81, (byte)0x0C, (byte)0x0C, (byte)0xC6, (byte)0xBA, (byte)0xC5, (byte)0xA7, (byte)0xAA, (byte)0x20, (byte)0xA7, (byte)0xC4, (byte)0xC5, (byte)0xAA, (byte)0xB5, (byte)0xC4};

	// private static final byte[] Directory = {
	// 	(byte)4, (byte)9, (byte)13, (byte)9,
	// 	(byte)0x44, (byte)0x69, (byte)0x72, (byte)0x65, (byte)0x63, (byte)0x74, (byte)0x6f, (byte)0x72, (byte)0x79,
	// 	(byte)0x81, (byte)0x06, (byte)0x0C, (byte)0xA7, (byte)0xC4, (byte)0xAF, (byte)0xC4, (byte)0xCA, (byte)0xC4};

	// private static final byte[] MobilePortal = {
	// 	(byte)4, (byte)13, (byte)17, (byte)15,
	// 	(byte)0x4d, (byte)0x6f, (byte)0x62, (byte)0x69, (byte)0x6c, (byte)0x65, (byte)0x20, (byte)0x50, (byte)0x6f, (byte)0x72, (byte)0x74, (byte)0x61, (byte)0x6c,
	// 	(byte)0x81, (byte)0x0C, (byte)0x0C, (byte)0xC5, (byte)0xC8, (byte)0xC2, (byte)0xB9, (byte)0x20, (byte)0xA7, (byte)0xAA, (byte)0xB5, (byte)0xA7, (byte)0xC4, (byte)0xA7, (byte)0xAA};

	// private static final byte[] Police = {
	// 	(byte)4, (byte)6, (byte)10, (byte)9,
	// 	(byte)0x50, (byte)0x6f, (byte)0x6c, (byte)0x69, (byte)0x63, (byte)0x65,
	// 	(byte)0x81, (byte)0x06, (byte)0x0C, (byte)0xA7, (byte)0xC4, (byte)0xB4, (byte)0xB1, (byte)0xB7, (byte)0xA9};

	// private static final byte[] Ambulance = {
	// 	(byte)4, (byte)9, (byte)13, (byte)10,
	// 	(byte)0x41, (byte)0x6d, (byte)0x62, (byte)0x75, (byte)0x6c, (byte)0x61, (byte)0x6e, (byte)0x63, (byte)0x65,
	// 	(byte)0x81, (byte)0x07, (byte)0x0C, (byte)0xA7, (byte)0xC4, (byte)0xA5, (byte)0xB3, (byte)0xB9, (byte)0xA7, (byte)0xC1};

	// private static final byte[] FireBrigade = {
	// 	(byte)4, (byte)12, (byte)16, (byte)10,
	// 	(byte)0x46, (byte)0x69, (byte)0x72, (byte)0x65, (byte)0x20, (byte)0x42, (byte)0x72, (byte)0x69, (byte)0x67, (byte)0x61, (byte)0x64, (byte)0x65,
	// 	(byte)0x81, (byte)0x07, (byte)0x0C, (byte)0xA7, (byte)0xC4, (byte)0xC5, (byte)0xB7, (byte)0xA7, (byte)0xC1, (byte)0xA6};
	
	// private static final byte[] SecretNumber = {
	// 	(byte)4, (byte)13, (byte)17, (byte)22,
	// 	(byte)0x53, (byte)0x65, (byte)0x63, (byte)0x72, (byte)0x65, (byte)0x74, (byte)0x20, (byte)0x4E, (byte)0x75, (byte)0x6D, (byte)0x62, (byte)0x65, (byte)0x72,
	// 	(byte)0x06, (byte)0x27, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x42, (byte)0x06, (byte)0x45, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x33, (byte)0x06, (byte)0x31, (byte)0x06, (byte)0x49};

	// private static final byte[] Amount = {
	// 	(byte)4, (byte)6, (byte)10, (byte)12,
	// 	(byte)0x41, (byte)0x6D, (byte)0x6F, (byte)0x75, (byte)0x6E, (byte)0x74,
	// 	(byte)0x06, (byte)0x27, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0x28, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x3A};

	// private static final byte[] Recipient = {
	// 	(byte)4, (byte)9, (byte)13, (byte)22,
	// 	(byte)0x52, (byte)0x65, (byte)0x63, (byte)0x69, (byte)0x70, (byte)0x69, (byte)0x65, (byte)0x6E, (byte)0x74,
	// 	(byte)0x06, (byte)0x27, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x45, (byte)0x06, (byte)0x2D, (byte)0x06, (byte)0x48, (byte)0x06, (byte)0x44, (byte)0x00, (byte)0x20, (byte)0x06, (byte)0x27, (byte)0x06, (byte)0x44, (byte)0x06, (byte)0x4A, (byte)0x06, (byte)0x47};
	
	// private static final byte[] etisalat = {
	// 	(byte)4, (byte)47, (byte)4, (byte)44,
	// 	(byte)'h', (byte)'t', (byte)'t', (byte)'p', (byte)'s', (byte)':', (byte)'/', (byte)'/', (byte)'w', (byte)'w', (byte)'w', (byte)'.', (byte)'e', (byte)'t', (byte)'i', (byte)'s', (byte)'a', (byte)'l', (byte)'a', (byte)'t', (byte)'.', (byte)'e', (byte)'g', (byte)'/', (byte)'e', (byte)'t', (byte)'i', (byte)'s', (byte)'a', (byte)'l', (byte)'a', (byte)'t', (byte)'/', (byte)'p', (byte)'o', (byte)'r', (byte)'t', (byte)'a', (byte)'l', (byte)'/', (byte)'h', (byte)'o', (byte)'m', (byte)'e', (byte)'_', (byte)'e', (byte)'n'};

	// private static final byte[] Calling = {(byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'.', (byte)'.', (byte)'.'};
	
	private byte lang=0;
	private ToolkitRegistry mReg;
	private byte[] byteArray1 = JCSystem.makeTransientByteArray((short) 160,JCSystem.CLEAR_ON_RESET);
	private byte[] byteArray2 = JCSystem.makeTransientByteArray((short) 160,JCSystem.CLEAR_ON_RESET);
	//Item ID for number of items
	//1 Item => My AWCC
	private byte[] itemId = {(byte) 0x00, (byte) 0x00, (byte) 0x00};
	
	public AfghanWireless_STK() 
	{
		register();
		// get the GSM application reference
		// register to the SIM Toolkit Framework
		mReg = ToolkitRegistry.getEntry();		
		itemId[0] = mReg.initMenuEntry(Menu, (byte)Menu[lang], (short)Menu[(short)(lang+1)], (byte)0x00, false, (byte)0x00, (byte)0x00);//Menu //WHAT IS HAPPENING HERE??? HOW DOES THIS FUNCTION EXPAND
		itemId[1] = mReg.initMenuEntry(Language, (byte)Language[lang], (short)Language[(short)(lang+1)], (byte)0x00, false, (byte)0x00, (byte)0x00);//Language-(Dari)
		itemId[2] = mReg.initMenuEntry(Language, (byte)Language[lang], (short)Language[(short)(lang+2)], (byte)0x00, false, (byte)0x00, (byte)0x00);//Language-(Pashto)
	}

	public static void install(byte bArray[], short bOffset, byte bLength) 
	{
		new AfghanWireless_STK();
	}

	public Shareable getShareableInterfaceObject(AID clientAID, byte parameter) 
	{
		if (clientAID == null & parameter == (byte) 0x00) {
			return ((Shareable) this);
		}
		return (null);
	}

	private void ProcessEventMenuSelection (ProactiveHandler proHdlr, ProactiveResponseHandler rspHdlr)
	{
		short phase=100, result=0, input_len=0;//WHAT IS PHASE???
		EnvelopeHandler envHdlr = EnvelopeHandler.getTheHandler();
		result=envHdlr.getItemIdentifier();
		if(result==itemId[0])	phase=1;
		else if(result==itemId[1]){
			lang=(byte) ((lang==0)?2:0);//WHY CHANGING PHASE IF LANGUAGE OPTION CHOSEN
			mReg.changeMenuEntry(itemId[0], MyAccount, (byte)MyAccount[lang], (short)MyAccount[(short)(lang+1)],(byte) 0x00, false, (byte) 0x00, (short) 0x0000);
			mReg.changeMenuEntry(itemId[1], Services, (byte)Services[lang], (short)Services[(short)(lang+1)],(byte) 0x00, false, (byte) 0x00, (short) 0x0000);
			mReg.changeMenuEntry(itemId[2], Emergency, (byte)Emergency[lang], (short)Emergency[(short)(lang+1)],(byte) 0x00, false, (byte) 0x00, (short) 0x0000);
			mReg.changeMenuEntry(itemId[3], CustomerCare, (byte)CustomerCare[lang], (short)CustomerCare[(short)(lang+1)],(byte) 0x00, false, (byte) 0x00, (short) 0x0000);
			mReg.changeMenuEntry(itemId[4], Language, (byte)Language[lang], (short)Language[(short)(lang+1)],(byte) 0x00, false, (byte) 0x00, (short) 0x0000);
		}
		//if command response is OK 
		do{
			switch(phase)
			{
			case 1://MyAccount
				phase=100;
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER ), MyAccount,(byte)MyAccount[lang],(short)MyAccount[(short)(lang+1)]);
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)1, BalanceBill,(byte)BalanceBill[lang],(short)BalanceBill[(short)(lang+1)]); 
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)2, BalanceRefill,(byte)BalanceRefill[lang],(short)BalanceRefill[(short)(lang+1)]);
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)3, BalanceTransfer,(byte)BalanceTransfer[lang],(short)BalanceTransfer[(short)(lang+1)]);
				result = proHdlr.send();
				if(result == RES_CMD_PERF){
					phase = (short)(10+rspHdlr.getItemIdentifier());
				}
				break;
			case 11:
				phase=100;
				byteArray1[0] = '*';
				byteArray1[1] = '5';
				byteArray1[2] = '5';
				byteArray1[3] = '5';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 12://SecretNumber
				phase=100;
				byteArray1[0] = '*';
				byteArray1[1] = '5';
				byteArray1[2] = '5';
				byteArray1[3] = '6';
				byteArray1[4] = '*';
				proHdlr.initGetInput((byte)0x00,(byte)((lang==0)?0x04:0x08),SecretNumber,(short)SecretNumber[lang],(short)SecretNumber[(short)(lang+1)],(short)1,(short)20);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)5);
					byteArray1[(short)input_len] = '#';
					SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)(input_len+1));
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=1;
				}
				break;
			case 13://Amount
				phase=100;
				byteArray1[0] = '*';
				byteArray1[1] = '5';
				byteArray1[2] = '5';
				byteArray1[3] = '7';
				byteArray1[4] = '*';
				proHdlr.initGetInput((byte)0x00,(byte)((lang==0)?0x04:0x08),Amount,(short)Amount[lang],(short)Amount[(short)(lang+1)],(short)1,(short)4);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)5);
					byteArray1[(short)input_len] = '*';
					phase=131;
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=1;
				}
				break;
			case 131://Recipient
				phase=100;
				proHdlr.initGetInput((byte)0x00,(byte)((lang==0)?0x04:0x08),Recipient,(short)Recipient[lang],(short)Recipient[(short)(lang+1)],(short)1,(short)22);//alpha 01, numeric 00
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)(input_len+1));
					byteArray1[(short)input_len] = '#';
					SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)(input_len+1));
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=13;
				}
				break;
			case 2://Services
				phase=100;
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER ), Services,(byte)Services[lang],(short)Services[(short)(lang+1)]);
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)1, VoiceMail,(byte)VoiceMail[lang],(short)VoiceMail[(short)(lang+1)]); 
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)2, RingBackTone,(byte)RingBackTone[lang],(short)RingBackTone[(short)(lang+1)]);
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)3, Directory,(byte)Directory[lang],(short)Directory[(short)(lang+1)]);
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)4, MobilePortal,(byte)MobilePortal[lang],(short)MobilePortal[(short)(lang+1)]);
				result = proHdlr.send();
				if(result == RES_CMD_PERF){
					phase = (short)(20+rspHdlr.getItemIdentifier());
				}
				break;
			case 21://VoiceMail
				phase=100;
				byteArray1[0] = (byte)0x81;
				byteArray1[1] = (byte)0x44;
				byteArray1[2] = (byte)0xF4;
				setUpCall(proHdlr, byteArray1, (short)3);
				break;
			case 22://RingBackTone
				phase=100;
				byteArray1[0] = (byte)0x81;
				byteArray1[1] = (byte)0x51;
				byteArray1[2] = (byte)0x00;
				setUpCall(proHdlr, byteArray1, (short)3);
				break;
			case 23://Directory
				phase=100;
				byteArray1[0] = (byte)0x81;
				byteArray1[1] = (byte)0x41;
				byteArray1[2] = (byte)0xF0;
				setUpCall(proHdlr, byteArray1, (short)3);
				break;
			case 24://MobilePortal
				phase=100;
				LaunchBrowser(proHdlr,etisalat,(byte)etisalat[lang],(short)etisalat[(short)(lang+1)]);
				break;
			case 3://Emergency
				phase=100;
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER ), Emergency,(byte)Emergency[lang],(short)Emergency[(short)(lang+1)]);
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)1, Police,(byte)Police[lang],(short)Police[(short)(lang+1)]); 
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)2, Ambulance,(byte)Ambulance[lang],(short)Ambulance[(short)(lang+1)]);
				proHdlr.appendTLV((byte) (TAG_ITEM ),(byte)3, FireBrigade,(byte)FireBrigade[lang],(short)FireBrigade[(short)(lang+1)]);
				result = proHdlr.send();
				if(result == RES_CMD_PERF){
					phase = (short)(30+rspHdlr.getItemIdentifier());
				}
				break;
			case 31://Police
				phase=100;
				byteArray1[0] = (byte)0x81;
				byteArray1[1] = (byte)0x21;
				byteArray1[2] = (byte)0xF2;
				setUpCall(proHdlr, byteArray1, (short)3);
				break;
			case 32://Ambulance
				phase=100;
				byteArray1[0] = (byte)0x81;
				byteArray1[1] = (byte)0x21;
				byteArray1[2] = (byte)0xF3;
				setUpCall(proHdlr, byteArray1, (short)3);
				break;
			case 33://FireBrigade
				phase=100;
				byteArray1[0] = (byte)0x81;
				byteArray1[1] = (byte)0x81;
				byteArray1[2] = (byte)0xF0;
				setUpCall(proHdlr, byteArray1, (short)3);
				break;
			case 4:
				phase=100;
				byteArray1[0] = (byte)0x81;
				byteArray1[1] = (byte)0x33;
				byteArray1[2] = (byte)0xF3;
				setUpCall(proHdlr, byteArray1, (short)3);
				break;
			}
		}while(phase!=100);
	}

	public void process(APDU apdu) throws ISOException {
		if (selectingApplet()) {
			return;
		}

		byte[] buffer = apdu.getBuffer();

		switch (buffer[ISO7816.OFFSET_CLA]) {
		case (byte) 0xB0:

			break;

		default:
			ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
			break;
		}
	}

	public void processToolkit(byte event) throws ToolkitException {
		// TODO Auto-generated method stub
		ProactiveHandler         proHdlr = ProactiveHandler.getTheHandler();
		ProactiveResponseHandler rspHdlr = ProactiveResponseHandler.getTheHandler();

		switch(event) 
		{
		case EVENT_MENU_SELECTION:
			ProcessEventMenuSelection(proHdlr,rspHdlr);
			break;			
		}
	}

	//setup call 
	private void setUpCall(ProactiveHandler proHdlr, byte[] caddress, short calladdrlen) 
	{
		proHdlr.init(PRO_CMD_SET_UP_CALL, (byte) 0x00, DEV_ID_NETWORK);
		proHdlr.appendTLV(TAG_ADDRESS, caddress, (short) 0, (short) calladdrlen);
		proHdlr.appendTLV(TAG_ALPHA_IDENTIFIER, Calling, (short) 0, (short) Calling.length);
		proHdlr.send();
		return;
	}
	
	private byte LaunchBrowser(ProactiveHandler proHdlr, byte[] arr, short off, short len){
		proHdlr.init((byte)0x15,(byte)0x00,DEV_ID_ME);//Launch Browser
		//proHdlr.appendTLV((byte)0x31, Launch_URL,(short)11,(short)15);
		proHdlr.appendTLV((byte)0x31, arr,(short)off,(short)len);
		return proHdlr.send();
	}

	//Method to send USSD command 
	//By Default szParam1 is the USSD String
	public final void SendUSSD(ProactiveHandler proHdlr, ProactiveResponseHandler rspHdlr ,byte[] ussd, short ussdoffset, short ussdlen)
	{
		//initialize the array to be used with FF 
		Util.arrayFillNonAtomic(byteArray2,(short)0,(short)byteArray2.length,(byte)0xFF);
		//copy the USSD string into it , after packing  
		packString( ussd, (short)ussdlen, byteArray2 );
		//get the exact length without any padding 
		short len = RemoveAlphaTagPadding(byteArray2);
		//Initiate command 
		proHdlr.init(PRO_CMD_SEND_USSD,(byte)0x00,DEV_ID_NETWORK);
		//alpha id for sending 
		//proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER ), SendReq,(short)0,(short)SendReq.length);
		//copy ussd string  
		proHdlr.appendTLV((byte)0x0A,(byte)0x0F, byteArray2,(short)0,len);
		//send command 
		if (proHdlr.send()==RES_CMD_PERF)
		{
			Util.arrayFillNonAtomic(byteArray2,(short)0,(short)byteArray2.length,(byte)0xFF);
			try{
				len=rspHdlr.findAndCopyValue(TAG_TEXT_STRING, byteArray2, (short) 0x00);
			}catch(ToolkitException te){
				if(te.getReason()==ToolkitException.UNAVAILABLE_ELEMENT)
					return;
			}
			byte dcs = byteArray2[0];
			//len = RemoveAlphaTagPadding(byteArray2);
			//display result 
			proHdlr.initDisplayText((byte)0x81,dcs, byteArray2,(short)1,(short)(len-1));
			proHdlr.send();
		}
	}

	public final short RemoveAlphaTagPadding(byte[] data) 
	{
		short i;
		for(i=(short)0;i<(short)data.length;i++){
			if(data[i] == (byte)0xFF)
				break;
		}
		return (short)i;
	}

	/************************************
	 * packString
	 * **********************************/
	public final void packString(byte[] msg,short ulen, byte destArray[])
	{
		short i;

		short destbit = (short)0;
		short destbyte = (short)0;
		destArray[destbyte]=(byte)0x00;
		byte compbyte= (byte)0x00;
		for (i=1;i<=(short)(ulen*8);i++)
		{
			if (i%(short)8 != (short)0) 
			{
				destbit ++;
			}
			if (destbit >8)
			{
				destbyte++;
				destArray[destbyte]=(byte)0x00;
				destbit = (short)1;
			}
			if (i%(short)8 != (short)0) 
			{
				switch ((short)(i%(short)8))
				{
				case 0: compbyte=(byte)0x00; break;
				case 1: compbyte=(byte)0x01; break;
				case 2: compbyte=(byte)0x02; break;
				case 3: compbyte=(byte)0x04; break;
				case 4: compbyte=(byte)0x08; break;
				case 5: compbyte=(byte)0x10; break;
				case 6: compbyte=(byte)0x20; break;
				case 7: compbyte=(byte)0x40; break;
				}
				if ((compbyte & (byte)(msg[(short)(i/(short)8)])) > (byte)0x0) destArray[destbyte]=(byte)((byte)((byte)0x01<<(short)(destbit-1)) | (byte)destArray[destbyte] );
			}
		}
	}
	public void print(byte[] arr,short off, short len){
		ProactiveHandler proHdlr = ProactiveHandler.getTheHandler();//command 
		proHdlr.initDisplayText((byte)0x81, (byte)0x04, arr, (short)off, (short)len);//01: clear after delay/HP, 81: user input/HP
		proHdlr.send();
	}
}
