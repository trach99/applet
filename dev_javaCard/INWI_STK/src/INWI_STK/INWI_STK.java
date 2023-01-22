

/*
 * Copyright (c) 2001
 * BSD ?
 */

//
// $Workfile: CardEdge.java $
// $Revision$
// $Date$
// $Author$
// $Archive: CardEdge $
// $Modtime: 5/02/00 8:48p $
//

package INWI_STK;

import uicc.toolkit.EnvelopeHandler;
import uicc.toolkit.EnvelopeHandlerSystem;
import uicc.toolkit.ProactiveHandler;
import uicc.toolkit.ProactiveHandlerSystem;
import uicc.toolkit.ProactiveResponseHandler;
import uicc.toolkit.ToolkitConstants;
import uicc.toolkit.ToolkitException;
import uicc.toolkit.ToolkitInterface;
import uicc.toolkit.ToolkitRegistry;
import uicc.toolkit.ToolkitRegistrySystem;
import uicc.toolkit.ProactiveResponseHandlerSystem;

import javacard.framework.AID;
import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISOException;
import javacard.framework.JCSystem;
import javacard.framework.Shareable;
import javacard.framework.Util;
//import uicc.usim.access.USIMConstants;



public class INWI_STK extends Applet implements ToolkitConstants, ToolkitInterface {

	// Mandatory variables
	private ToolkitRegistry reg;
	final byte DCS_8_BIT_DATA = 0x04;


	public static final byte PRO_CMD_SEND_SHORT_MESSAGE = (byte) 0x13;
	public static final byte TAG_SMS_TPDU = (byte) 0x0B;

	private static final byte PRO_CMD_SEND_USSD = 0x12 ;
	private static final byte TAG_USSD_STRING = 0x0A ;
	
	private static byte generalResult = 0x00;



	public static byte[] dtItemText = {
			0x00,0x50,0x00,0x6F,0x00,0x75,0x00,0x72,0x00,0x20,0x00,0x61,0x00,0x63,0x00,0x63,0x00,(byte) 0xE9,0x00,0x64,0x00,0x65,0x00,0x72,0x00,0x20,0x00,
			(byte) 0xE0,0x00,0x20,0x00,0x6E,0x00,0x6F,0x00,0x74,0x00,0x72,0x00,0x65,0x00,0x20,0x00,0x63,0x00,0x61,0x00,0x74,0x00,0x61,0x00,0x6C,0x00,0x6F,0x00,0x67,0x00,
			0x75,0x00,0x65,0x00,0x20,0x00,0x64,0x00,0x65,0x00,0x20,0x00,0x73,0x00,0x65,0x00,0x72,0x00,0x76,0x00,0x69,0x00,0x63,0x00,0x65,0x00,0x73,0x00,0x20,0x00,0x61,0x00,
			0x70,0x00,0x70,0x00,0x65,0x00,0x6C,0x00,0x65,0x00,0x7A,0x00,0x20,0x00,0x6C,0x00,0x65,0x00,0x20,0x00,0x2A,0x00,0x31,0x00,0x32,0x00,0x30,0x00,0x2A,0x00,0x31,0x00,
			0x31,0x00,0x23,
	};
	
	private static byte[] tempArray ;
	private static byte[] tmpDataArray ;



// DT text
	private static byte[] confirmDt = new byte[] { 'C','o','n','f','i','r','m','e','r',' ','?'};

	private static byte[] menuIndex ;



	private INWI_STK(byte[] bArray, short bOffset, byte bLength) {
		byte aidLen = bArray[bOffset];
		menuIndex = new byte[6] ;
		tempArray = JCSystem.makeTransientByteArray((short)50, JCSystem.CLEAR_ON_RESET) ;
		tmpDataArray = JCSystem.makeTransientByteArray((short)25, JCSystem.CLEAR_ON_RESET) ;
		if (aidLen == (byte) 0) {
			register();
		} else {
			// Register this applet
			register(bArray, (short) (bOffset + 1),  bArray[bOffset]);
			initToolkit();
		}
	}

	public void initToolkit() {
		reg = ToolkitRegistrySystem.getEntry();
		short offset = (short)1 ;
		for(short counter = (short)0; counter <= (short)5; counter++)
		{

			menuIndex[(short)counter] = (byte)reg.initMenuEntry(ItemText.mainMenuText, offset, 
					(short)(ItemText.mainMenuText[(short)(offset - (short)1)] & 0x00FF), (byte)0, false, (byte)0, (short)0);
			offset = (short) (offset + (ItemText.mainMenuText[(short)(offset - (short)1)] & 0x00FF)+ (short)1) ;
		}


	}

	public static void install(byte bArray[], short bOffset, byte bLength) throws ISOException {
		new INWI_STK(bArray, bOffset, bLength);
	}

	public void process(APDU apdu) throws ISOException {
		// TODO Auto-generated method stub

	}


	/**
	 * This Method is for handling first menu.
	 * @param proHdlr, proactive handler reference
	 */
	private static void firstMenuItem(ProactiveHandler proHdlr)
	{

		switch(generateSelectItem(proHdlr, (byte)0x04, ItemText.serviceMobile, (short)0 ,ItemText.mainMenuText, (short)0))
		{
		case 1:
			tempArray[0] = 0x06 ;	tempArray[1] = (byte) 0x91 ;		tempArray[2] = 0x12 ;
			tempArray[3] = 0x52 ;			tempArray[4] = (byte) 0x92 ;
			tempArray[5] = 0x00 ;			tempArray[6] = 0x10 ;
			tempArray[7] = 0x32 ;
			sendsetUpCallCommand(proHdlr, (short)tempArray[0]);
			break;
		case 2:			
			tempArray[0] = 0x02 ;		tempArray[1] = (byte) 0x81;	tempArray[2] = 0x07 ; tempArray[3] = (byte) 0xF0 ;
			sendsetUpCallCommand(proHdlr, (short)tempArray[0]);
			break;
		case 3:
			tempArray[0] = 0x02 ;		tempArray[1] = (byte) 0x81;	tempArray[2] = (byte) 0x87 ; tempArray[3] = (byte) 0xF9 ;
			sendsetUpCallCommand(proHdlr, (short)tempArray[0]);
			break;
		case 4:
			proHdlr.init(PRO_CMD_PROVIDE_LOCAL_INFORMATION, (byte)0x01,
					DEV_ID_NETWORK);
			proHdlr.send();
			ProactiveResponseHandler rspHndlr = ProactiveResponseHandlerSystem.getTheHandler();
			short length = rspHndlr.findAndCopyValue(TAG_IMEI, tempArray, (short)5);
			ImeiParsing(tempArray, (byte)5, tmpDataArray, (byte)5, (byte)8);
			tmpDataArray[0] = 'I'; tmpDataArray[1] = 'N'; tmpDataArray[2] = 'W'; tmpDataArray[3] = 'I';tmpDataArray[4] = ' ';
			sendSMSArray(ItemText.smsNumbers, (short)6, (byte)2,tmpDataArray , (short)0, (short)(length+7), proHdlr);
			break;
		}

	}
	
	public static void ImeiParsing(byte[] srcArr,byte offset, byte[] dstArr, byte offsetDst, byte length)
	{
		dstArr[offsetDst++] =  (byte) (0x30 | ((srcArr[offset] & 0xF0) >> 4));
		for(byte counter = (byte) (offset + 0x01); counter < (byte) (offset + length); counter++)
		{
			dstArr[offsetDst++] = (byte) (0x30 | ((srcArr[counter] & 0xF0) >> 4));
			dstArr[offsetDst++] = (byte) (0x30 | (srcArr[counter] & 0x0F));
		}
	}

	/**
	 * This is supporting method for second menu TR handling
	 * @param proHdlr, proactive handler reference
	 * @param itemSelected, byte contain selected item value
	 */
	private static void secondItemIntermediateHandling(ProactiveHandler proHdlr, byte itemSelected)
	{
		byte result = 0x00 ;
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)0x03, ItemText.infosText, (short)0,ItemText.actualiteText,(short) 0) : itemSelected ;
		switch(item)
		{

		case 0:
			sendItem(proHdlr, (byte)0x05, ItemText.actualiteText, (short)0, ItemText.mainMenuText, (short)17,(byte)1);
			break;
		case 1:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.sportsText, (short)0,ItemText.infosText, (short) 0);
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short) 0, (short)9, proHdlr);
				
			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x03, ItemText.infosText, (short)0,ItemText.actualiteText,(short) 0,(byte)2);
				}
				else
				{
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2 , (short)0, (short)9, proHdlr);
				}
			}
			break;
		case 2:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.sportsText, (short)0,ItemText.infosText, (short) 6);
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short) 9, (short)9, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x03, ItemText.infosText, (short)0,ItemText.actualiteText,(short) 0,(byte)2);
				}
				else {
					sendDisplayText(proHdlr);	
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short) 9, (short)9, proHdlr);
				}
			}
			break;
		case 3:
			result = generateSelectItem(proHdlr, (byte)0x01, ItemText.sportsText, (short)0x00,ItemText.infosText, (short) 20) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr) ;	
				sendSMS(ItemText.smsNumbers, (short)4, (byte)2, (short)0, (short)4, proHdlr);

			}
			else
				sendItem(proHdlr, (byte)0x03, ItemText.infosText, (short)0,ItemText.actualiteText,(short) 0,(byte)2);
			break;
		}

	}
	
	/**
	 * This method is to generate SI and set next action accordingly
	 * @param proHdlr, proactive handler
	 * @param noOfItem, number of item to generate
	 * @param bArray, Array containing item text
	 * @param offset, offset to start
	 * @param nextAction, next action accordingly
	 */
	private static void sendItem(ProactiveHandler proHdlr, byte noOfItem, byte[] bArray, short offset, byte[] bAlpaArray, short alpaOffset, byte nextAction)
	{

		byte result = generateSelectItem(proHdlr, noOfItem, bArray, offset,bAlpaArray,alpaOffset) ;
		if(0x00 == result)
		{
			result = 0x00 ;
		}
			

		switch(nextAction)
		{
		case 0x01: 
			secondItemHandling(proHdlr,result);
			break ;
		case 0x02:
			secondItemIntermediateHandling(proHdlr,result);
			break;
		case 0x03:
			thirdMenuHandling(proHdlr,result);
			break;
		case 0x04:
			thirdItemIntermediateHandling(proHdlr,result);
			break;
		case 0x05:
			trainItemHandling(proHdlr,result) ;
			break;
		case 0x06:
			pharmaciesItemHandling(proHdlr,result);
			break;
		case 0x07:
			meteoMenuHandling(proHdlr,result);
			break ;
		case 0x08:
			secondItemFootballHandling(proHdlr,result);
			break;
		}
		
	
	}
	
	private static void secondItemFootballHandling(ProactiveHandler proHdlr, byte itemSelected)
	{
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)0x03, ItemText.footballText, (short)0,ItemText.actualiteText,(short) 12) : itemSelected ; 
		switch(item)
		{
		case 0: 
			sendItem(proHdlr, (byte)0x05, ItemText.actualiteText, (short)0, ItemText.mainMenuText, (short)17,(byte) 0x01);
			break ;
		case 1:
			switch(generateSelectItem(proHdlr, (byte)0x06, ItemText.resultsText, (short)0, ItemText.footballText, (short)0)) 
			{
			case 0x00:
				sendItem(proHdlr, (byte)0x03, ItemText.footballText, (short)0,ItemText.actualiteText,(short) 12,(byte) 0x08);
				break ;
			case 0x01:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2 , (short) 23, (short)7, proHdlr);
				break;
			case 0x02: 

				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short) 30, (short)7, proHdlr);
				break ;
			case 0x03:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short) 37, (short)6, proHdlr);
				break ;
			case 0x04:
				sendDisplayText(proHdlr);	
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)43, (short)6, proHdlr);						
				break;
			case 0x05:
				sendDisplayText(proHdlr);	
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)49, (short)5, proHdlr);						
				break ;
			case 0x06:
				sendDisplayText(proHdlr);	
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)54, (short)5, proHdlr);						

				break;
			}
			break ;
		case 2:
			switch(generateSelectItem(proHdlr, (byte)0x06, ItemText.resultsText, (short)0, ItemText.footballText, (short)20))
			{
			case 0x00:
				sendItem(proHdlr, (byte)0x03, ItemText.footballText, (short)0,ItemText.actualiteText,(short) 12,(byte) 0x08);
				break ;
			case 0x01:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)59, (short)9, proHdlr);
				break;
			case 0x02:

				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)68, (short)9, proHdlr);
				break;
			case 0x03:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)77, (short)8, proHdlr);
				break;
			case 0x04:
				sendDisplayText(proHdlr);	
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)85, (short)8, proHdlr);						
				break;
			case 0x05:
				sendDisplayText(proHdlr);	
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)93, (short)7, proHdlr);						
				break ;
			case 0x06:
				sendDisplayText(proHdlr);	
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)100, (short)7, proHdlr);						

				break;
			}
			break ;
		case 3:
			sendDisplayText(proHdlr);
			sendSMS(ItemText.smsNumbers, (short)4, (byte)2, (short)107, (short)4, proHdlr);
			break;
		}

	}

	/**
	 * This method is used for managing second item TR
	 * @param proHdlr, proactive handler reference
	 */
	private static void secondItemHandling(ProactiveHandler proHdlr, byte itemSelected)
	{
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)0x05, ItemText.actualiteText, (short)0, ItemText.mainMenuText, (short)17) : itemSelected ;
		switch(item)
		{
		case 1:
			secondItemIntermediateHandling(proHdlr,(byte) 0x50);
			break;
		case 2:
			switch(generateSelectItem(proHdlr, (byte)0x02, ItemText.sportsText, (short)0,ItemText.actualiteText,(short) 6))
			{
			case 0:
				sendItem(proHdlr, (byte)0x05, ItemText.actualiteText, (short)0, ItemText.mainMenuText, (short)17,(byte) 0x01);
				break ;
			case 1:
				sendDisplayText(proHdlr) ;	
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short) 18, (short)5, proHdlr);

				break;
			case 2:
				sendDisplayText(proHdlr) ;	
				sendSMS(ItemText.smsNumbers, (short)2,(byte)2, (short) 18, (short)5, proHdlr);

				break;
			}
			break;
		case 3:
			secondItemFootballHandling(proHdlr, (byte) 0x50);
			break;
		case 4:
			switch(generateSelectItem(proHdlr, (byte)0x01, ItemText.sportsText, (short)0x00, ItemText.actualiteText, (short)21))
			{
			case 0:
				sendItem(proHdlr, (byte)0x05, ItemText.actualiteText, (short)0, ItemText.mainMenuText, (short)17,(byte) 0x01);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)4, (byte)2, (short)111, (short)5, proHdlr);
				break;
			}
			break;
		case 5:
			switch(generateSelectItem(proHdlr, (byte)0x02, ItemText.sportsText, (short)0, ItemText.actualiteText, (short)29))
			{
			case 0:
				sendItem(proHdlr, (byte)0x05, ItemText.actualiteText, (short)0, ItemText.mainMenuText, (short)17,(byte) 0x01);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)116, (short)6, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)116, (short)6, proHdlr);

				break;
			}
			break;
		}

	}


	/**
	 * This is helper method for item third, this is to manage TR handling
	 * @param proHdlr, proactive handler reference
	 */
	private static void thirdItemIntermediateHandling(ProactiveHandler proHdlr, byte itemSelected)
	{
		byte result = 0x00 ;
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)12, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32) : itemSelected ;
		switch(item)
		{
		case 0 :
			thirdMenuHandling(proHdlr,(byte) 0x50);
			break ;
		case 1:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)0) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)122, (short)6, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)122, (short)6, proHdlr);
				}
			}
			break;
		case 2:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)14) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)128, (short)7, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)128, (short)7, proHdlr);
				}
			}
			break;
		case 3:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)22) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)135, (short)7, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)135, (short)7, proHdlr);
				}
			}
			break;
		case 4:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)38) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)142, (short)6, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)142, (short)6, proHdlr);
				}
			}
			break;
		case 5:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)45) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)148, (short)4, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)148, (short)4, proHdlr);
				}
			}
			break;
		case 6:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)50) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)152, (short)6, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)152, (short)6, proHdlr);
				}
			}
			break;
		case 7:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)57) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)158, (short)7, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)158, (short)7, proHdlr);
				}
			}
			break;
		case 8:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)65) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)165, (short)8, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)165, (short)8, proHdlr);
				}
			}
			break;
		case 9:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)74) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)173, (short)10, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)173, (short)10, proHdlr);
				}
			}
			break;
		case 0x0A:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)85) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)183, (short)10, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)183, (short)10, proHdlr);
				}
			}
			break;
		case 0x0B:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)96) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)193, (short)7, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)193, (short)7, proHdlr);
				}
			}
			break;
		case 0x0C:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.belierText, (short)0, ItemText.horoscopeText, (short)104) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)200, (short)8, proHdlr);

			}
			else
			{
				if(result == 0x00)
				{
					sendItem(proHdlr, (byte)0x0C, ItemText.horoscopeText, (short)0, ItemText.divertissementText, (short)32, (byte)4);
				}
				else {
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)200, (short)8, proHdlr);
				}
			}
			break;
		}

	}
	
	/**
	 * This method is for managing third menu item flow
	 * @param proHdlr, proactive handler reference
	 */
	private static void thirdMenuHandling(ProactiveHandler proHdlr, byte itemSelected)
	{
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)0x03, ItemText.divertissementText, (short)0, ItemText.mainMenuText, (short)37) : itemSelected ;
		switch(item)
		{
		case 1:
			switch(generateSelectItem(proHdlr, (byte)0x03, ItemText.programmText, (short)0, ItemText.divertissementText, (short)0))
			{
			case 0x00:
				sendItem(proHdlr, (byte)0x03, ItemText.divertissementText, (short)0, ItemText.mainMenuText, (short)37, (byte)3);
				break ;
			case 0x01:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)208, (short)8, proHdlr);

				break;
			case 0x02:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)216, (short)9, proHdlr);

				break;
			case 0x03:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)225, (short)5, proHdlr);

				break;
			}
			break;
		case 2:
			thirdItemIntermediateHandling(proHdlr,(byte) 0x50);
			break;
		case 3:
			switch(generateSelectItem(proHdlr, (byte)0x02, ItemText.citationText, (short)0, ItemText.divertissementText, (short)42))
			{
			case 0:
				sendItem(proHdlr, (byte)0x03, ItemText.divertissementText, (short)0, ItemText.mainMenuText, (short)37, (byte)3);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)230, (short)8, proHdlr);

				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)230, (short)8, proHdlr);

				break;
			}
			break;
		}

	}
	
	/**
	 * This is supporting method in forth menu which handle Meteo Menu
	 * @param proHdlr, Proactive handler reference
	 */
	private static void meteoMenuHandling(ProactiveHandler proHdlr, byte itemSelected)
	{
		byte result = 0x00 ;
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)52) : itemSelected ;
		switch(item)
		{
		case 0:
			forthMenuHanding(proHdlr);
			break;
		case 1:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)0);
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)238, (short)8, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
					sendDisplayText(proHdlr);
					sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)238, (short)8, proHdlr);
				}
			}
			break;
		case 2:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)7) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)246, (short)9, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)246, (short)9, proHdlr);
				}
			}
			break;
		case 3:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)18) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)255, (short)8, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)255, (short)8, proHdlr);
				}
			}
			break;
		case 4:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)22) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)263, (short)9, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)263, (short)9, proHdlr);
				}
			}
			break;
		case 5:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)31) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)272, (short)10, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)272, (short)10, proHdlr);
				}
			}
			break;
		case 6:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)41) ; 
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)282, (short)8, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)282, (short)8, proHdlr);
				}
			}
			break;
		case 7:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)47);
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)290, (short)9, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)290, (short)9, proHdlr);
				}
			}
			break;
		case 8:
			result = generateSelectItem(proHdlr, (byte)0x02, ItemText.agadirText, (short)0, ItemText.meteoText, (short)53) ;
			if(0x01 == result)
			{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)0, (byte)2, (short)299, (short)8, proHdlr);
			}
			else
			{
				if(0x00 == result)
				{
					sendItem(proHdlr, (byte)0x08, ItemText.meteoText, (short)0, ItemText.pratiqueText, (short)40, (byte)7);
				}
				else
				{
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)299, (short)8, proHdlr);
				}
			}
			break;
		}

	}
	
	/**
	 * This is also supporting method for forth menu entry which handle Pharmacies item
	 * @param proHdlr, proactive handler reference
	 */
	private static void pharmaciesItemHandling(ProactiveHandler proHdlr, byte itemSelected)
	{
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)0x02, ItemText.pharmaciesText, (short)0, ItemText.pratiqueText, (short)0) : itemSelected ;
		switch(item)
		{
		case 0:
			forthMenuHanding(proHdlr);
			break ;
		case 1:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casablancaText, (short)0, ItemText.pharmaciesText, (short)0))
			{
			case 0:
				sendItem(proHdlr, (byte)0x02, ItemText.pharmaciesText, (short)0, ItemText.pratiqueText, (short)0, (byte)6);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)307, (short)8, proHdlr);

				break ;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)315, (short)8, proHdlr);

				break ;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)323, (short)8, proHdlr);
				break ;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)331, (short)8, proHdlr);
				break ;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)339, (short)8, proHdlr);
				break ;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)347, (short)8, proHdlr);
				break ;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)355, (short)8, proHdlr);
				break ;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)363, (short)9, proHdlr);
				break ;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)372, (short)9, proHdlr);
				break ;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)381, (short)9, proHdlr);
				break ;
			}
			break;
		case 2:
			switch(generateSelectItem(proHdlr, (byte)0x08, ItemText.rabatText, (short)0, ItemText.pharmaciesText, (short)11))
			{
			case 0:
				sendItem(proHdlr, (byte)0x02, ItemText.pharmaciesText, (short)0, ItemText.pratiqueText, (short)0, (byte)6);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)390, (short)8, proHdlr);
				break ;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)398, (short)8, proHdlr);
				break ;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)406, (short)8, proHdlr);
				break ;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)414, (short)8, proHdlr);
				break ;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)422, (short)8, proHdlr);
				break ;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)430, (short)8, proHdlr);
				break ;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)438, (short)8, proHdlr);
				break ;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)446, (short)9, proHdlr);
				break ;
			}
			break;
		}
	
	}
	
	/**
	 * This is supporting method for forth meu item
	 * @param proHdlr, proactive handler reference
	 */
	private static void trainItemHandling(ProactiveHandler proHdlr,byte itemSelected)
	{
		byte item = (itemSelected == 0x50) ? generateSelectItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0) : itemSelected ;
		switch(item)
		{
		case 0:
			forthMenuHanding(proHdlr);
			break ;
		case 1:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)455, (short)25, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)480, (short)30, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)510, (short)25, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)535, (short)19, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)554, (short)23, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)577, (short)25, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)602, (short)22, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)624, (short)21, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2773, (short)27, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
//				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)677, (short)27, proHdlr);
				sendSMSArray(ItemText.smsNumbers, (short)2, (byte)2, ItemText.tangerCasaport, (short)0, (short)ItemText.tangerCasaport.length, proHdlr);
				break;
			}
			break;
		case 2:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)704, (short)30, proHdlr);
				
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)734, (short)35, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)769, (short)30, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)799, (short)24, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)823, (short)28, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)851, (short)30, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)881, (short)27, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)908, (short)26, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)934, (short)32, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)966, (short)27, proHdlr);
				break;
			}
			break;
		case 3:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)993, (short)25, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1018, (short)30, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1048, (short)25, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1073, (short)19, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1097, (short)23, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1120, (short)25, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1145, (short)22, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1167, (short)21, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1188, (short)27, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1215, (short)22, proHdlr);
				break;
			}
			break;
		case 4:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1237, (short)19, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1256, (short)24, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1280, (short)19, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1299, (short)13, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1312, (short)17, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1329, (short)19, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1348, (short)16, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1364, (short)15, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1379, (short)21, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1400, (short)16, proHdlr);
				break;
			}
			break;
		case 5:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1416, (short)23, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1439, (short)28, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1467, (short)23, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1490, (short)17, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1507, (short)21, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1528, (short)23, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1551, (short)20, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1571, (short)19, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1590, (short)25, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1615, (short)20, proHdlr);
				break;
			}
			break;
		case 6:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0: 
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1635, (short)25, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1660, (short)30, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1690, (short)25, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1715, (short)19, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1734, (short)23, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1757, (short)25, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1782, (short)22, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1804, (short)21, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1825, (short)27, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1852, (short)22, proHdlr);
				break;
			}
			break;
		case 7:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1874, (short)22, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1896, (short)27, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1923, (short)22, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1945, (short)16, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1961, (short)20, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)1981, (short)22, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2003, (short)19, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2022, (short)18, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2040, (short)24, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2064, (short)19, proHdlr);
				break;
			}
			break;
		case 8:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2083, (short)21, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2104, (short)26, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2130, (short)21, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2151, (short)15, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2166, (short)19, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2185, (short)21, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2206, (short)18, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2224, (short)17, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2241, (short)23, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2264, (short)18, proHdlr);
				break;
			}
			break;
		case 9:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break ;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2282, (short)27, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2309, (short)32, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2341, (short)27, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2368, (short)21, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2389, (short)25, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2414, (short)27, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2441, (short)24, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2465, (short)23, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2488, (short)29, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2517, (short)24, proHdlr);
				break;
			}
			break;
		case 0x0A:
			switch(generateSelectItem(proHdlr, (byte)0x0A, ItemText.casaPortText, (short)0, ItemText.alphaIds, (short)24))
			{
			case 0:
				sendItem(proHdlr, (byte)0x0A, ItemText.trainsText, (short)0, ItemText.alphaIds, (short)0, (byte)5);
				break;
			case 1:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2541, (short)22, proHdlr);
				break;
			case 2:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2563, (short)27, proHdlr);
				break;
			case 3:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2590, (short)22, proHdlr);
				break;
			case 4:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2612, (short)16, proHdlr);
				break;
			case 5:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2628, (short)20, proHdlr);
				break;
			case 6:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2648, (short)22, proHdlr);
				break;
			case 7:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2670, (short)19, proHdlr);
				break;
			case 8:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2689, (short)18, proHdlr);
				break;
			case 9:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2707, (short)24, proHdlr);
				break;
			case 0x0A:
				sendDisplayText(proHdlr);
				sendSMS(ItemText.smsNumbers, (short)2, (byte)2, (short)2731, (short)19, proHdlr);
				break;
			}
			break;
		}

	}
	
	/**
	 * This method handle forth menu item
	 * @param proHdlr, proactive handler reference
	 */
	private static void forthMenuHanding(ProactiveHandler proHdlr)
	{

		switch(generateSelectItem(proHdlr, (byte)0x04, ItemText.pratiqueText, (short)0, ItemText.mainMenuText, (short)52))
		{
		case 1:
			pharmaciesItemHandling(proHdlr,(byte) 0x50);
			break;
		case 2:
			trainItemHandling(proHdlr,(byte) 0x50);
			break;
		case 3:
			proHdlr.initGetInput((byte)0x01, (byte)0x04,ItemText.newsMessage, (short)2750, (short)23, (short)1, (short)15);
			if(0x00 == proHdlr.send())
			{
				short length = ProactiveResponseHandlerSystem.getTheHandler().findAndCopyValue(TAG_TEXT_STRING, tmpDataArray, (short)4);
				// setting text 'SALAT'
				tmpDataArray[0] = 'S';
				tmpDataArray[1] = 'A';
				tmpDataArray[2] = 'L';
				tmpDataArray[3] = 'A';
				tmpDataArray[4] = 'T';
				sendDisplayText(proHdlr);
				sendSMSArray(ItemText.smsNumbers, (short)2, (byte)2,tmpDataArray, (short)0, length, proHdlr);
			}
			break;
		case 4:
			meteoMenuHandling(proHdlr ,(byte) 0x50);
			break;
		}
	
	}
	/**
	 * This is toolkit method which executes on the bases of events.
	 */
	public void processToolkit(short event) throws ToolkitException {

		EnvelopeHandler envHdlr = EnvelopeHandlerSystem.getTheHandler();


		if (event == EVENT_MENU_SELECTION) {
			ProactiveHandler proHdlr = ProactiveHandlerSystem.getTheHandler();
			byte itemId = envHdlr.getItemIdentifier() ;

			if(itemId == menuIndex[0x00])
			{
				firstMenuItem(proHdlr) ;
			}
			if(itemId == menuIndex[0x01])
			{
				secondItemHandling(proHdlr, (byte) 0x50);
			}
			if(itemId == menuIndex[0x02])
			{
				thirdMenuHandling(proHdlr, (byte) 0x50);
			}
			if(itemId == menuIndex[0x03])
			{
				forthMenuHanding(proHdlr);
			}
			if(itemId == menuIndex[0x04])
			{
				proHdlr.initDisplayText((byte)0x81, (byte)0x08, dtItemText, (short)0, (short)124);
				proHdlr.send() ;
			}
			if(itemId == menuIndex[0x05])
			{

				tempArray[0] = 0x06 ;	tempArray[1] = (byte) 0x91 ;		tempArray[2] = 0x12 ; tempArray[3] = (byte) 0x52 ;
				tempArray[4] = (byte) 0x92 ;			tempArray[5] = 0x00 ; tempArray[6] = (byte) 0x20 ;
				 tempArray[7] = (byte) 0x02 ;
				sendsetUpCallCommand( proHdlr, (short)tempArray[0]);
			}

		} 

	}

	/**
	 * this method is for send set up call command.
	 * @param number, Buffer that contains number to call.
	 */
	private static void sendsetUpCallCommand( ProactiveHandler proHdlr, short length)
	{
		proHdlr.init(PRO_CMD_SET_UP_CALL, (byte)0x00,
				DEV_ID_NETWORK); 
		proHdlr.appendTLV(TAG_ADDRESS, tempArray, (short)1,
				(short)(length + 1)); 
		proHdlr.send();
	}

	/**
	 * This Method is for sending display Text with Confirmer ?
	 * @param proHdlr
	 */
	private static void sendDisplayText(ProactiveHandler proHdlr)
	{
		proHdlr.initDisplayText((byte)0x81, (byte)0x04, confirmDt, (short)0, (short)confirmDt.length);
		generalResult = proHdlr.send() ;
		
	}

	/**
	 * This method is for sending SMS
	 * @param destAddr, byte array contains destination address
	 * @param offset, offset of dest. address
	 * @param length, length of dest. address
	 * @param data, byte array containing data of SMS
	 * @param offsetData, Offset of data
	 * @param lengthData, length of SMS data
	 * @param proHdlr, proactive handler reference
	 */
	private static void sendSMS(byte[] destAddr, short offset, byte length, short offsetData, short lengthData, ProactiveHandler proHdlr)
	{
		if(generalResult == 0x11)
		{
			return ;
		}

		// TP-MTI
		tempArray[0]  = (byte)0x01;
		// TP-MR
		tempArray[1]  = (byte)0x01;
		// TP-DA length
		tempArray[2] = (byte) ((destAddr[(short)(offset+length-1)]&0xF0) == 0xF0 ? (length*0x02)-0x01:length*0x02) ;
		// TP-DA
		//80 = unknown, 81 = national number, 91 = international number
		tempArray[3]  = (byte)0x81;
		short offsetToBuild = (short)  Util.arrayCopy(destAddr, (short)offset, tempArray, (short)4, (short)(length));

		
		// TP-PID     
		tempArray[offsetToBuild++]  = (byte)0x00;
		// TP-DCS
		tempArray[offsetToBuild++]  = (byte)0x04;

		// TP-UDL
		tempArray[offsetToBuild++] = (byte)lengthData;

		// Copy the input string into tempBuffer[]                 
		short valueLength=(short) (Util.arrayCopy(ItemText.newsMessage, offsetData, tempArray, offsetToBuild, (short)(lengthData)));

//
	
		proHdlr.init(PRO_CMD_SEND_SHORT_MESSAGE,(byte) 0x00,
				DEV_ID_NETWORK);
		proHdlr.appendTLV(TAG_SMS_TPDU, tempArray, (short)0, (short)(valueLength));
		// Send the command to the mobile
		if(proHdlr.send() == 0x20 )
		{
			if(proHdlr.send() == 0x20 )
			{
				if(proHdlr.send() == 0x20 )
				{
					if(proHdlr.send() == 0x20 )
					{
						return ;
					}
				}
			}
		}
	}
	
	/**
	 * This method is for sending SMS
	 * @param destAddr, byte array contains destination address
	 * @param offset, offset of dest. address
	 * @param length, length of dest. address
	 * @param data, byte array containing data of SMS
	 * @param offsetData, Offset of data
	 * @param lengthData, length of SMS data
	 * @param proHdlr, proactive handler reference
	 */
	private static void sendSMSArray(byte[] destAddr, short offset, byte length,byte[] dataArray, short offsetData, short lengthData, ProactiveHandler proHdlr)
	{
		
		if(generalResult == 0x11)
		{
			return ;
		}
		// TP-MTI
		tempArray[0]  = (byte)0x01;
		// TP-MR
		tempArray[1]  = (byte)0x01;
		// TP-DA length
		tempArray[2] = (byte) ((destAddr[(short)(offset+length-1)]&0xF0) == 0xF0 ? (length*0x02)-0x01:length*0x02) ;
		// TP-DA
		//80 = unknown, 81 = national number, 91 = international number
		tempArray[3]  = (byte)0x81;
		short offsetToBuild = (short)  Util.arrayCopy(destAddr, (short)offset, tempArray, (short)4, (short)(length));
		
		// TP-PID     
		tempArray[offsetToBuild++]  = (byte)0x00;
		// TP-DCS
		tempArray[offsetToBuild++]  = (byte)0x04;

		// TP-UDL
		tempArray[offsetToBuild++] = (byte)lengthData;

		// Copy the input string into tempBuffer[]                 
		short valueLength=(short) (Util.arrayCopy(dataArray, offsetData, tempArray, offsetToBuild, (short)(lengthData)));
//
//		proHdlr.initDisplayText((byte)0x81, (byte)0x04, tempArray, (short)0, (short)valueLength);
//		proHdlr.send();
		proHdlr.init(PRO_CMD_SEND_SHORT_MESSAGE,(byte) 0x00,
				DEV_ID_NETWORK);
		proHdlr.appendTLV(TAG_SMS_TPDU, tempArray, (short)0, (short)(valueLength));
		// Send the command to the mobile
		
		if(proHdlr.send() == 0x20 )
		{
			if(proHdlr.send() == 0x20 )
			{
				if(proHdlr.send() == 0x20 )
				{
					if(proHdlr.send() == 0x20 )
					{
						return ;
					}
				}
			}
		}
	}

	/**
	 *  * This Method is for sending display Text with Confirmer ?
	 * @param proHdlr, proactive handler reference
	 * @param noOfItem, no of item to be created
	 * @param bArray, byte array containing item data
	 * @param offset, offset in array
	 * @return, item selected and 0 if back action is pressed.
	 */
	private static byte generateSelectItem(ProactiveHandler proHdlr, byte noOfItem, byte[] bArray, short offset, byte[] alphaIdentifier, short offsetAlpha)
	{
		proHdlr.init(
				PRO_CMD_SELECT_ITEM,
				(byte)(0),
				DEV_ID_TERMINAL);
		proHdlr.appendTLV(TAG_ALPHA_IDENTIFIER, alphaIdentifier, (short)(offsetAlpha+1), alphaIdentifier[offsetAlpha]);

		for(byte counter = 0x01; counter <= noOfItem; counter++)
		{
			proHdlr.appendTLV(
					(byte)(TAG_ITEM | TAG_SET_CR),
					counter,
					bArray,
					(short)(offset + 1),
					(short)(bArray[offset] & 0x00FF));
			offset = (short) (offset + (bArray[offset] & 0x00FF) + (short)1) ;
		}
		if(0x11 == proHdlr.send())
			return 0x00 ;

		return ProactiveResponseHandlerSystem.getTheHandler().getItemIdentifier();
	}



	public Shareable getShareableInterfaceObject(AID clientAID, byte parameter) {
		if ((parameter == (byte) 0x01) && (clientAID == null)) {
			return ((Shareable) this);
		}
		return null;
	}

	public void uninstall() {
		// TODO Auto-generated method stub

	}

} // end of class
