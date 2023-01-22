package Africell_STK;

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

public class Africell_STK extends javacard.framework.Applet implements
ToolkitInterface, ToolkitConstants {

	private static final byte[] Account = {(byte)'A', (byte)'c', (byte)'c', (byte)'o', (byte)'u', (byte)'n', (byte)'t'};
	private static final byte[] Africell_Services = {(byte)'A', (byte)'f', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)'l', (byte)'l', (byte)' ', (byte)'S', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)'s'};
	private static final byte[] Afrimoney = {(byte)'A', (byte)'f', (byte)'r', (byte)'i', (byte)'m', (byte)'o', (byte)'n', (byte)'e', (byte)'y'};
	private static final byte[] Entertainment = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)'t', (byte)'a', (byte)'i', (byte)'n', (byte)'m', (byte)'e', (byte)'n', (byte)'t'};

	private static final byte[] Check_My_Airtime_Bal = {(byte)'C', (byte)'h', (byte)'e', (byte)'c', (byte)'k', (byte)' ', (byte)'M', (byte)'y', (byte)' ', (byte)'A', (byte)'i', (byte)'r', (byte)'t', (byte)'i', (byte)'m', (byte)'e', (byte)' ', (byte)'B', (byte)'a', (byte)'l', (byte)'a', (byte)'n', (byte)'c', (byte)'e'};
	private static final byte[] Check_My_Data_Bal = {(byte)'C', (byte)'h', (byte)'e', (byte)'c', (byte)'k', (byte)' ', (byte)'M', (byte)'y', (byte)' ', (byte)'D', (byte)'a', (byte)'t', (byte)'a', (byte)' ', (byte)'B', (byte)'a', (byte)'l', (byte)'a', (byte)'n', (byte)'c', (byte)'e'};
	private static final byte[] Top_Up_Account = {(byte)'T', (byte)'o', (byte)'p', (byte)' ', (byte)'U', (byte)'p', (byte)' ', (byte)'A', (byte)'c', (byte)'c', (byte)'o', (byte)'u', (byte)'n', (byte)'t'};
	private static final byte[] Transfer_Credit = {(byte)'T', (byte)'r', (byte)'a', (byte)'n', (byte)'s', (byte)'f', (byte)'e', (byte)'r', (byte)' ', (byte)'C', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'t'};
	private static final byte[] Call_Me = {(byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'M', (byte)'e'};
	private static final byte[] Collect_Call = {(byte)'C', (byte)'o', (byte)'l', (byte)'l', (byte)'e', (byte)'c', (byte)'t', (byte)' ', (byte)'C', (byte)'a', (byte)'l', (byte)'l'};
	private static final byte[] Send_Me_Credits = {(byte)'S', (byte)'e', (byte)'n', (byte)'d', (byte)' ', (byte)'M', (byte)'e', (byte)' ', (byte)'C', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'t', (byte)'s'};
	private static final byte[] LENme_Credit = {(byte)'L', (byte)'E', (byte)'N', (byte)'m', (byte)'e', (byte)' ', (byte)'C', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'t'};
	private static final byte[] Maintain_your_Line = {(byte)'M', (byte)'a', (byte)'i', (byte)'n', (byte)'t', (byte)'a', (byte)'i', (byte)'n', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'L', (byte)'i', (byte)'n', (byte)'e'};
	private static final byte[] Check_Your_number = {(byte)'C', (byte)'h', (byte)'e', (byte)'c', (byte)'k', (byte)' ', (byte)'Y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'n', (byte)'u', (byte)'m', (byte)'b', (byte)'e', (byte)'r'};
	private static final byte[] Customer_Care = {(byte)'C', (byte)'u', (byte)'s', (byte)'t', (byte)'o', (byte)'m', (byte)'e', (byte)'r', (byte)' ', (byte)'C', (byte)'a', (byte)'r', (byte)'e'};

	private static final byte[] Africell_Data_Bundles = {(byte)'A', (byte)'f', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)'l', (byte)'l', (byte)' ', (byte)'D', (byte)'a', (byte)'t', (byte)'a', (byte)' ', (byte)'B', (byte)'u', (byte)'n', (byte)'d', (byte)'l', (byte)'e', (byte)'s'};
	private static final byte[] Africell_Ya_Mix_Bundles = {(byte)'A', (byte)'f', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)'l', (byte)'l', (byte)' ', (byte)'Y', (byte)'a', (byte)' ', (byte)'M', (byte)'i', (byte)'x', (byte)'+',(byte)' ', (byte)'B', (byte)'u', (byte)'n', (byte)'d', (byte)'l', (byte)'e', (byte)'s'};
	private static final byte[] Africell_SMB = {(byte)'A', (byte)'f', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)'l', (byte)'l', (byte)' ', (byte)'S', (byte)'M', (byte)'B'};
	private static final byte[] SMS_Bundles = {(byte)'S', (byte)'M', (byte)'S', (byte)' ', (byte)'B', (byte)'u', (byte)'n', (byte)'d', (byte)'l', (byte)'e', (byte)'s'};
	private static final byte[] Roaming = {(byte)'R', (byte)'o', (byte)'a', (byte)'m', (byte)'i', (byte)'n', (byte)'g'};

	private static final byte[] Fun_Ring_Tone = {(byte)'F', (byte)'u', (byte)'n', (byte)' ', (byte)'R', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'T', (byte)'o', (byte)'n', (byte)'e'};
	private static final byte[] Song_for_You = {(byte)'S', (byte)'o', (byte)'n', (byte)'g', (byte)' ', (byte)'f', (byte)'o', (byte)'r', (byte)' ', (byte)'Y', (byte)'o', (byte)'u'};
	private static final byte[] Live_Score = {(byte)'L', (byte)'i', (byte)'v', (byte)'e', (byte)' ', (byte)'S', (byte)'c', (byte)'o', (byte)'r', (byte)'e'};
	private static final byte[] AYV_News = {(byte)'A', (byte)'Y', (byte)'V', (byte)' ', (byte)'N', (byte)'e', (byte)'w', (byte)'s'};
	private static final byte[] Biblical_Quotes = {(byte)'B', (byte)'i', (byte)'b', (byte)'l', (byte)'i', (byte)'c', (byte)'a', (byte)'l', (byte)' ', (byte)'Q', (byte)'u', (byte)'o', (byte)'t', (byte)'e', (byte)'s'};
	private static final byte[] Quran_Recitation = {(byte)'Q', (byte)'u', (byte)'r', (byte)'a', (byte)'n', (byte)' ', (byte)'R', (byte)'e', (byte)'c', (byte)'i', (byte)'t', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n'};
	private static final byte[] Love_Meter = {(byte)'L', (byte)'o', (byte)'v', (byte)'e', (byte)' ', (byte)'M', (byte)'e', (byte)'t', (byte)'e', (byte)'r'};
	private static final byte[] Star_Sign = {(byte)'S', (byte)'t', (byte)'a', (byte)'r', (byte)' ', (byte)'S', (byte)'i', (byte)'g', (byte)'n'};
	private static final byte[] Name_Analyzer = {(byte)'N', (byte)'a', (byte)'m', (byte)'e', (byte)' ', (byte)'A', (byte)'n', (byte)'a', (byte)'l', (byte)'y', (byte)'z', (byte)'e', (byte)'r'};
	private static final byte[] Daily_Horoscope = {(byte)'D', (byte)'a', (byte)'i', (byte)'l', (byte)'y', (byte)' ', (byte)'H', (byte)'o', (byte)'r', (byte)'o', (byte)'s', (byte)'c', (byte)'o', (byte)'p', (byte)'e'};

	private static final byte[] Whats_Call_Me = {(byte)'W', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'M', (byte)'e', (byte)' ', (byte)'S', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)'?'};
	private static final byte[] Use_Call_Me = {(byte)'U', (byte)'s', (byte)'e', (byte)' ', (byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'M', (byte)'e'};
	private static final byte[] Whats_Collect_Call = {(byte)'W', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'C', (byte)'o', (byte)'l', (byte)'l', (byte)'e', (byte)'c', (byte)'t', (byte)' ', (byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'S', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)'?'};
	private static final byte[] Use_Collect_Call = {(byte)'U', (byte)'s', (byte)'e', (byte)' ', (byte)'C', (byte)'o', (byte)'l', (byte)'l', (byte)'e', (byte)'c', (byte)'t', (byte)' ', (byte)'C', (byte)'a', (byte)'l', (byte)'l'};
	private static final byte[] Whats_LENme_Credit = {(byte)'W', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'L', (byte)'E', (byte)'N', (byte)'m', (byte)'e', (byte)' ', (byte)'C', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'t', (byte)' ', (byte)'S', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)'?'};
	private static final byte[] Subscribe = {(byte)'S', (byte)'u', (byte)'b', (byte)'s', (byte)'c', (byte)'r', (byte)'i', (byte)'b', (byte)'e'};
	private static final byte[] Unsubscribe = {(byte)'U', (byte)'n', (byte)'s', (byte)'u', (byte)'b', (byte)'s', (byte)'c', (byte)'r', (byte)'i', (byte)'b', (byte)'e'};
	private static final byte[] Whats_maintain_your_Line = {(byte)'W', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'m', (byte)'a', (byte)'i', (byte)'n', (byte)'t', (byte)'a', (byte)'i', (byte)'n', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'L', (byte)'i', (byte)'n', (byte)'e', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e'};
	private static final byte[] Call_Customer_Care = {(byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'C', (byte)'u', (byte)'s', (byte)'t', (byte)'o', (byte)'m', (byte)'e', (byte)'r', (byte)' ', (byte)'C', (byte)'a', (byte)'r', (byte)'e'};
	private static final byte[] Daily_SMS_Bundle = {(byte)'D', (byte)'a', (byte)'i', (byte)'l', (byte)'y', (byte)' ', (byte)'S', (byte)'M', (byte)'S', (byte)' ', (byte)'B', (byte)'u', (byte)'n', (byte)'d', (byte)'l', (byte)'e'};
	private static final byte[] Monthly_SMS_Bundle = {(byte)'M', (byte)'o', (byte)'n', (byte)'t', (byte)'h', (byte)'l', (byte)'y', (byte)' ', (byte)'S', (byte)'M', (byte)'S', (byte)' ', (byte)'B', (byte)'u', (byte)'n', (byte)'d', (byte)'l', (byte)'e'};
	private static final byte[] What_is_Roaming = {(byte)'W', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'R', (byte)'o', (byte)'a', (byte)'m', (byte)'i', (byte)'n', (byte)'g', (byte)'?'};
	private static final byte[] Activate_Prepaid_Roaming = {(byte)'A', (byte)'c', (byte)'t', (byte)'i', (byte)'v', (byte)'a', (byte)'t', (byte)'e', (byte)' ', (byte)'P', (byte)'r', (byte)'e', (byte)'p', (byte)'a', (byte)'i', (byte)'d', (byte)' ', (byte)'R', (byte)'o', (byte)'a', (byte)'m', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'S', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e'};
	private static final byte[] Call_RBT_Agent = {(byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'R', (byte)'B', (byte)'T', (byte)' ', (byte)'A', (byte)'g', (byte)'e', (byte)'n', (byte)'t'};
	private static final byte[] Send_a_song_to_someone = {(byte)'S', (byte)'e', (byte)'n', (byte)'d', (byte)' ', (byte)'a', (byte)' ', (byte)'s', (byte)'o', (byte)'n', (byte)'g', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'s', (byte)'o', (byte)'m', (byte)'e', (byte)'o', (byte)'n', (byte)'e'};
	private static final byte[] Get_match_reminders = {(byte)'G', (byte)'e', (byte)'t', (byte)' ', (byte)'m', (byte)'a', (byte)'t', (byte)'c', (byte)'h', (byte)' ', (byte)'r', (byte)'e', (byte)'m', (byte)'i', (byte)'n', (byte)'d', (byte)'e', (byte)'r', (byte)'s', (byte)' ', (byte)'a', (byte)'n', (byte)'d', (byte)' ', (byte)'L', (byte)'I', (byte)'V', (byte)'E', (byte)' ', (byte)'m', (byte)'a', (byte)'t', (byte)'c', (byte)'h', (byte)' ', (byte)'u', (byte)'p', (byte)'d', (byte)'a', (byte)'t', (byte)'e', (byte)'s', (byte)' ', (byte)'f', (byte)'r', (byte)'o', (byte)'m', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'T', (byte)'o', (byte)'p', (byte)' ', (byte)'l', (byte)'e', (byte)'a', (byte)'g', (byte)'u', (byte)'e', (byte)'s', (byte)' ', (byte)'a', (byte)'r', (byte)'o', (byte)'u', (byte)'n', (byte)'d', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'w', (byte)'o', (byte)'r', (byte)'l', (byte)'d'};
	private static final byte[] AYV_News_update = {(byte)'A', (byte)'Y', (byte)'V', (byte)' ', (byte)'N', (byte)'e', (byte)'w', (byte)'s', (byte)' ', (byte)'u', (byte)'p', (byte)'d', (byte)'a', (byte)'t', (byte)'e', (byte)',', (byte)' ', (byte)'f', (byte)'o', (byte)'r', (byte)' ', (byte)'l', (byte)'o', (byte)'c', (byte)'a', (byte)'l', (byte)' ', (byte)'a', (byte)'n', (byte)'d', (byte)' ', (byte)'i', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)'n', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)'a', (byte)'l', (byte)' ', (byte)'n', (byte)'e', (byte)'w', (byte)'s'};
	private static final byte[] Subs_Daily_Bible_Quotes = {(byte)'S', (byte)'u', (byte)'b', (byte)'s', (byte)'c', (byte)'r', (byte)'i', (byte)'b', (byte)'e', (byte)' ', (byte)'t', (byte)'o', (byte)'o', (byte)' ', (byte)'g', (byte)'e', (byte)'t', (byte)' ', (byte)'D', (byte)'a', (byte)'i', (byte)'l', (byte)'y', (byte)' ', (byte)'B', (byte)'i', (byte)'b', (byte)'l', (byte)'e', (byte)' ', (byte)'Q', (byte)'u', (byte)'o', (byte)'t', (byte)'e', (byte)'s'};
	private static final byte[] Quranic_recitation_of_your_choice = {(byte)'Q', (byte)'u', (byte)'r', (byte)'a', (byte)'n', (byte)'i', (byte)'c', (byte)' ', (byte)'r', (byte)'e', (byte)'c', (byte)'i', (byte)'t', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)' ', (byte)'o', (byte)'f', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'c', (byte)'h', (byte)'o', (byte)'i', (byte)'c', (byte)'e'};
	private static final byte[] Enter_your_name = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'n', (byte)'a', (byte)'m', (byte)'e', (byte)':'};
	private static final byte[] Enter_your_sign = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'s', (byte)'i', (byte)'g', (byte)'n', (byte)':'};
	private static final byte[] Get_astrological_meaning = {(byte)'G', (byte)'e', (byte)'t', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'a', (byte)'s', (byte)'t', (byte)'r', (byte)'o', (byte)'l', (byte)'o', (byte)'g', (byte)'i', (byte)'c', (byte)'a', (byte)'l', (byte)' ', (byte)'m', (byte)'e', (byte)'a', (byte)'n', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'o', (byte)'f', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'s', (byte)'t', (byte)'a', (byte)'r', (byte)' ', (byte)'s', (byte)'i', (byte)'g', (byte)'n', (byte)' ', (byte)'d', (byte)'a', (byte)'i', (byte)'l', (byte)'y'};

	private static final byte[] Enter_the_serial_number = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'i', (byte)'a', (byte)'l', (byte)' ', (byte)'n', (byte)'u', (byte)'m', (byte)'b', (byte)'e', (byte)'r', (byte)':'};
	private static final byte[] Send_Leones_amount = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'L', (byte)'e', (byte)'o', (byte)'n', (byte)'e', (byte)'s', (byte)' ', (byte)'a', (byte)'m', (byte)'o', (byte)'u', (byte)'n', (byte)'t', (byte)' ', (byte)'t', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'w', (byte)'o', (byte)'u', (byte)'l', (byte)'d', (byte)' ', (byte)'l', (byte)'i', (byte)'k', (byte)'e', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'s', (byte)'e', (byte)'n', (byte)'d', (byte)':'};
	private static final byte[] Service_Please_call_me = {(byte)'T', (byte)'h', (byte)'i', (byte)'s', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'w', (byte)'i', (byte)'l', (byte)'l', (byte)' ', (byte)'a', (byte)'l', (byte)'l', (byte)'o', (byte)'w', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'s', (byte)'e', (byte)'n', (byte)'d', (byte)' ', (byte)'a', (byte)'n', (byte)' ', (byte)'S', (byte)'M', (byte)'S', (byte)' ', (byte)'c', (byte)'o', (byte)'n', (byte)'t', (byte)'a', (byte)'i', (byte)'n', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'\"', (byte)'P', (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'e', (byte)' ', (byte)'c', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'m', (byte)'e', (byte)'\"', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'a', (byte)'n', (byte)'y', (byte)' ', (byte)'n', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)'a', (byte)'l', (byte)' ', (byte)'o', (byte)'r', (byte)' ', (byte)'i', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)'n', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)'a', (byte)'l', (byte)' ', (byte)'n', (byte)'u', (byte)'m', (byte)'b', (byte)'e', (byte)'r', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'w', (byte)'i', (byte)'s', (byte)'h', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'c', (byte)'o', (byte)'n', (byte)'t', (byte)'a', (byte)'c', (byte)'t', (byte)' ', (byte)'w', (byte)'h', (byte)'e', (byte)'n', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'d', (byte)'o', (byte)' ', (byte)'n', (byte)'o', (byte)'t', (byte)' ', (byte)'h', (byte)'a', (byte)'v', (byte)'e', (byte)' ', (byte)'s', (byte)'u', (byte)'f', (byte)'f', (byte)'i', (byte)'c', (byte)'i', (byte)'e', (byte)'n', (byte)'t', (byte)' ', (byte)'c', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'t', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'c', (byte)'a', (byte)'l', (byte)'l', (byte)'.'};
	private static final byte[] Enter_the_Target_Number = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'T', (byte)'a', (byte)'r', (byte)'g', (byte)'e', (byte)'t', (byte)' ', (byte)'N', (byte)'u', (byte)'m', (byte)'b', (byte)'e', (byte)'r', (byte)':'};
	private static final byte[] Called_party_is_charged = {(byte)'T', (byte)'h', (byte)'i', (byte)'s', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'w', (byte)'i', (byte)'l', (byte)'l', (byte)' ', (byte)'a', (byte)'l', (byte)'l', (byte)'o', (byte)'w', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'c', (byte)'o', (byte)'n', (byte)'d', (byte)'u', (byte)'c', (byte)'t', (byte)' ', (byte)'a', (byte)' ', (byte)'c', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'w', (byte)'h', (byte)'i', (byte)'l', (byte)'e', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'c', (byte)'a', (byte)'l', (byte)'l', (byte)'e', (byte)'d', (byte)' ', (byte)'p', (byte)'a', (byte)'r', (byte)'t', (byte)'y', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'c', (byte)'h', (byte)'a', (byte)'r', (byte)'g', (byte)'e', (byte)'d', (byte)' ', (byte)'f', (byte)'o', (byte)'r', (byte)' ', (byte)'t', (byte)'h', (byte)'i', (byte)'s', (byte)' ', (byte)'c', (byte)'a', (byte)'l', (byte)'l', (byte)' ', (byte)'w', (byte)'h', (byte)'e', (byte)'n', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'d', (byte)'o', (byte)' ', (byte)'n', (byte)'o', (byte)'t', (byte)' ', (byte)'h', (byte)'a', (byte)'v', (byte)'e', (byte)' ', (byte)'s', (byte)'u', (byte)'f', (byte)'f', (byte)'i', (byte)'c', (byte)'i', (byte)'e', (byte)'n', (byte)'t', (byte)' ', (byte)'c', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'t', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'c', (byte)'a', (byte)'l', (byte)'l', (byte)'.'};
	private static final byte[] Request_Leones_amount = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'L', (byte)'e', (byte)'o', (byte)'n', (byte)'e', (byte)'s', (byte)' ', (byte)'a', (byte)'m', (byte)'o', (byte)'u', (byte)'n', (byte)'t', (byte)' ', (byte)'t', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'w', (byte)'a', (byte)'n', (byte)'t', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'r', (byte)'e', (byte)'q', (byte)'u', (byte)'e', (byte)'s', (byte)'t', (byte)':'};
	private static final byte[] Maintain_negative_credits = {(byte)'T', (byte)'h', (byte)'i', (byte)'s', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'w', (byte)'i', (byte)'l', (byte)'l', (byte)' ', (byte)'a', (byte)'l', (byte)'l', (byte)'o', (byte)'w', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'m', (byte)'a', (byte)'i', (byte)'n', (byte)'t', (byte)'a', (byte)'i', (byte)'n', (byte)' ', (byte)'n', (byte)'e', (byte)'g', (byte)'a', (byte)'t', (byte)'i', (byte)'v', (byte)'e', (byte)' ', (byte)'c', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'t', (byte)'s', (byte)' ', (byte)'b', (byte)'a', (byte)'s', (byte)'e', (byte)'d', (byte)' ', (byte)'o', (byte)'n', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'y', (byte)'e', (byte)'a', (byte)'r', (byte)'s', (byte)' ', (byte)'w', (byte)'i', (byte)'t', (byte)'h', (byte)' ', (byte)'A', (byte)'f', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)'l', (byte)'l', (byte)'.'};
	private static final byte[] Maintain_num_for_a_period = {(byte)'T', (byte)'h', (byte)'i', (byte)'s', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'e', (byte)'n', (byte)'a', (byte)'b', (byte)'l', (byte)'e', (byte)'s', (byte)' ', (byte)'s', (byte)'u', (byte)'b', (byte)'s', (byte)'c', (byte)'r', (byte)'i', (byte)'b', (byte)'e', (byte)'r', (byte)'s', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'m', (byte)'a', (byte)'i', (byte)'n', (byte)'t', (byte)'a', (byte)'i', (byte)'n', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)'i', (byte)'r', (byte)' ', (byte)'n', (byte)'u', (byte)'m', (byte)'b', (byte)'e', (byte)'r', (byte)'s', (byte)' ', (byte)'f', (byte)'o', (byte)'r', (byte)' ', (byte)'a', (byte)' ', (byte)'p', (byte)'a', (byte)'r', (byte)'t', (byte)'i', (byte)'c', (byte)'u', (byte)'l', (byte)'a', (byte)'r', (byte)' ', (byte)'p', (byte)'e', (byte)'r', (byte)'i', (byte)'o', (byte)'d', (byte)' ', (byte)'e', (byte)'s', (byte)'p', (byte)'e', (byte)'c', (byte)'i', (byte)'a', (byte)'l', (byte)'l', (byte)'y', (byte)' ', (byte)'i', (byte)'f', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)'y', (byte)' ', (byte)'w', (byte)'i', (byte)'l', (byte)'l', (byte)' ', (byte)'b', (byte)'e', (byte)' ', (byte)'t', (byte)'r', (byte)'a', (byte)'v', (byte)'e', (byte)'l', (byte)'l', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'a', (byte)'n', (byte)'o', (byte)'t', (byte)'h', (byte)'e', (byte)'r', (byte)' ', (byte)'c', (byte)'o', (byte)'u', (byte)'n', (byte)'t', (byte)'r', (byte)'y', (byte)' ', (byte)'b', (byte)'y', (byte)' ', (byte)'a', (byte)'c', (byte)'t', (byte)'i', (byte)'v', (byte)'a', (byte)'t', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'m', (byte)'a', (byte)'i', (byte)'n', (byte)'t', (byte)'a', (byte)'i', (byte)'n', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'l', (byte)'i', (byte)'n', (byte)'e', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)'.'};
	private static final byte[] Purchase_5_local_SMS = {(byte)'T', (byte)'h', (byte)'i', (byte)'s', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'a', (byte)'l', (byte)'l', (byte)'o', (byte)'w', (byte)'s', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'p', (byte)'u', (byte)'r', (byte)'c', (byte)'h', (byte)'a', (byte)'s', (byte)'e', (byte)' ', (byte)'5', (byte)' ', (byte)'l', (byte)'o', (byte)'c', (byte)'a', (byte)'l', (byte)' ', (byte)'S', (byte)'M', (byte)'S', (byte)' ', (byte)'f', (byte)'o', (byte)'r', (byte)' ', (byte)'a', (byte)' ', (byte)'c', (byte)'h', (byte)'e', (byte)'a', (byte)'p', (byte)'e', (byte)'r', (byte)' ', (byte)'p', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'r', (byte)'a', (byte)'t', (byte)'e', (byte)'.'};
	private static final byte[] Purchase_30_daily_SMS = {(byte)'T', (byte)'h', (byte)'i', (byte)'s', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'a', (byte)'l', (byte)'l', (byte)'o', (byte)'w', (byte)'s', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'p', (byte)'u', (byte)'r', (byte)'c', (byte)'h', (byte)'a', (byte)'s', (byte)'e', (byte)' ', (byte)'3', (byte)'0', (byte)' ', (byte)'d', (byte)'a', (byte)'i', (byte)'l', (byte)'y', (byte)' ', (byte)'S', (byte)'M', (byte)'S', (byte)' ', (byte)'o', (byte)'n', (byte)' ', (byte)'a', (byte)' ', (byte)'s', (byte)'i', (byte)'n', (byte)'g', (byte)'l', (byte)'e', (byte)' ', (byte)'s', (byte)'u', (byte)'b', (byte)'s', (byte)'c', (byte)'r', (byte)'i', (byte)'p', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)' ', (byte)'a', (byte)'n', (byte)'d', (byte)' ', (byte)'a', (byte)'t', (byte)' ', (byte)'a', (byte)' ', (byte)'c', (byte)'h', (byte)'e', (byte)'a', (byte)'p', (byte)'e', (byte)'r', (byte)' ', (byte)'p', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'c', (byte)'o', (byte)'s', (byte)'t', (byte)'.'};
	private static final byte[] Roaming_outside_S_Leone = {(byte)'R', (byte)'o', (byte)'a', (byte)'m', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'i', (byte)'s', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'s', (byte)'e', (byte)'r', (byte)'v', (byte)'i', (byte)'c', (byte)'e', (byte)' ', (byte)'t', (byte)'h', (byte)'a', (byte)'t', (byte)' ', (byte)'o', (byte)'f', (byte)'f', (byte)'e', (byte)'r', (byte)'s', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)' ', (byte)'t', (byte)'h', (byte)'e', (byte)' ', (byte)'o', (byte)'p', (byte)'p', (byte)'o', (byte)'r', (byte)'t', (byte)'u', (byte)'n', (byte)'i', (byte)'t', (byte)'y', (byte)' ', (byte)'t', (byte)'o', (byte)' ', (byte)'m', (byte)'a', (byte)'k', (byte)'e', (byte)' ', (byte)'a', (byte)'n', (byte)'d', (byte)' ', (byte)'r', (byte)'e', (byte)'c', (byte)'e', (byte)'i', (byte)'v', (byte)'e', (byte)' ', (byte)'c', (byte)'a', (byte)'l', (byte)'l', (byte)'s', (byte)' ', (byte)'o', (byte)'u', (byte)'t', (byte)'s', (byte)'i', (byte)'d', (byte)'e', (byte)' ', (byte)'S', (byte)'i', (byte)'e', (byte)'r', (byte)'r', (byte)'a', (byte)' ', (byte)'L', (byte)'e', (byte)'o', (byte)'n', (byte)'e', (byte)' ', (byte)'w', (byte)'i', (byte)'t', (byte)'h', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'A', (byte)'f', (byte)'r', (byte)'i', (byte)'c', (byte)'e', (byte)'l', (byte)'l', (byte)' ', (byte)'S', (byte)'L', (byte)' ', (byte)'S', (byte)'I', (byte)'M', (byte)' ', (byte)'c', (byte)'a', (byte)'r', (byte)'d', (byte)'.'};
	private static final byte[] Enter_your_friend_name = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'f', (byte)'r', (byte)'i', (byte)'e', (byte)'n', (byte)'d', (byte)' ', (byte)'n', (byte)'a', (byte)'m', (byte)'e', (byte)':'};
	private static final byte[] Enter_your_friend_s_Sign = {(byte)'E', (byte)'n', (byte)'t', (byte)'e', (byte)'r', (byte)' ', (byte)'y', (byte)'o', (byte)'u', (byte)'r', (byte)' ', (byte)'f', (byte)'r', (byte)'i', (byte)'e', (byte)'n', (byte)'d', (byte)'\'', (byte)'s', (byte)' ', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)':'};
	
	private static final byte[] Bal = {(byte)'B', (byte)'a', (byte)'l'};
	private static final byte[] sub = {(byte)'s', (byte)'u', (byte)'b'};
	private static final byte[] unsub = {(byte)'u', (byte)'n', (byte)'s', (byte)'u', (byte)'b'};
	private static final byte[] Sub = {(byte)'S', (byte)'u', (byte)'b'};
	private static final byte[] Unsub = {(byte)'U', (byte)'n', (byte)'s', (byte)'u', (byte)'b'};
	private static final byte[] start = {(byte)'s', (byte)'t', (byte)'a', (byte)'r', (byte)'t'};
	
	private static final byte[] by_SMS = {(byte)'b', (byte)'y', (byte)' ', (byte)'S', (byte)'M', (byte)'S'};
	private static final byte[] by_USSD = {(byte)'b', (byte)'y', (byte)' ', (byte)'U', (byte)'S', (byte)'S', (byte)'D'};
	private static final byte[] by_Calling_IVR = {(byte)'b', (byte)'y', (byte)' ', (byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)'i', (byte)'n', (byte)'g', (byte)' ', (byte)'I', (byte)'V', (byte)'R'};
	
	private static final short MAIN_MENU = 100;
	private static final byte[] Calling = {(byte)'C', (byte)'a', (byte)'l', (byte)'l', (byte)'i', (byte)'n', (byte)'g', (byte)'.', (byte)'.', (byte)'.'};
	
	private ToolkitRegistry mReg;
	private byte[] byteArray1 = JCSystem.makeTransientByteArray((short) 160,JCSystem.CLEAR_ON_RESET);
	private byte[] byteArray2 = JCSystem.makeTransientByteArray((short) 160,JCSystem.CLEAR_ON_RESET);
	private byte[] destNum = JCSystem.makeTransientByteArray((short) 10,JCSystem.CLEAR_ON_RESET);
	private byte[] itemId = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};

	public Africell_STK() {
		register();
		// get the GSM application reference
		// register to the SIM Toolkit Framework
		mReg = ToolkitRegistry.getEntry();		
		itemId[0] = mReg.initMenuEntry(Account, (short)0, (short)Account.length, (byte)0x00, false, (byte)0x00, (byte)0x00);//MyAccount
		itemId[1] = mReg.initMenuEntry(Africell_Services, (short)0, (short)Africell_Services.length, (byte)0x00, false, (byte)0x00, (byte)0x00);//Services
		itemId[2] = mReg.initMenuEntry(Afrimoney, (short)0, (short)Afrimoney.length, (byte)0x00, false, (byte)0x00, (byte)0x00);//Emergency
		itemId[3] = mReg.initMenuEntry(Entertainment, (short)0, (short)Entertainment.length, (byte)0x00, false, (byte)0x00, (byte)0x00);//Emergency
	}

	public static void install(byte bArray[], short bOffset, byte bLength) {
		new Africell_STK();
	}

	public Shareable getShareableInterfaceObject(AID clientAID, byte parameter) {
		if (clientAID == null & parameter == (byte) 0x00) {
			return ((Shareable) this);
		}
		return (null);
	}

	private void ProcessEventMenuSelection (ProactiveHandler proHdlr, ProactiveResponseHandler rspHdlr)
	{
		short phase=100, result=0, input_len=0;
		EnvelopeHandler envHdlr = EnvelopeHandler.getTheHandler();
		result=envHdlr.getItemIdentifier();
		if(result==itemId[0])	phase=1;
		else if(result==itemId[1])	phase=2;
		else if(result==itemId[2])	phase=3;
		else if(result==itemId[3])	phase=4;

		//if command response is OK 
		do{
			switch(phase)
			{
			case 1://Account
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Account,(short)0,(short)Account.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Check_My_Airtime_Bal,(short)0,(short)Check_My_Airtime_Bal.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Check_My_Data_Bal,(short)0,(short)Check_My_Data_Bal.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, Top_Up_Account,(short)0,(short)Top_Up_Account.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)4, Transfer_Credit,(short)0,(short)Transfer_Credit.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)5, Call_Me,(short)0,(short)Call_Me.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)6, Collect_Call,(short)0,(short)Collect_Call.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)7, Send_Me_Credits,(short)0,(short)Send_Me_Credits.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)8, LENme_Credit,(short)0,(short)LENme_Credit.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)9, Maintain_your_Line,(short)0,(short)Maintain_your_Line.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)10, Check_Your_number,(short)0,(short)Check_Your_number.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)11, Customer_Care,(short)0,(short)Customer_Care.length);
				phase = HandleNavigation(MAIN_MENU, (short)MAIN_MENU, MAIN_MENU,(short)10);//back, ok, abort, nextOffset
				break;
			case 11://Account->Check_My_Airtime_Bal
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Check_My_Airtime_Bal,(short)0,(short)Check_My_Airtime_Bal.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, by_SMS,(short)0,(short)by_SMS.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, by_USSD,(short)0,(short)by_USSD.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, by_Calling_IVR,(short)0,(short)by_Calling_IVR.length);
				phase = HandleNavigation((short)1, (short)0, MAIN_MENU,(short)110);//back, ok, abort, nextOffset
				destNum[0] = (byte)0x03;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x41;
				destNum[3] = (byte)0xF0;
				break;
			case 111://Account->Check_My_Airtime_Bal->by_SMS
				SendSMS(proHdlr, Bal, (short)0, (short)3, destNum, (short)4);//140
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 112://Account->Check_My_Airtime_Bal->by_USSD
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '1';
				byteArray1[2] = '4';
				byteArray1[3] = '0';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 113://Account->Check_My_Airtime_Bal->by_Calling_IVR
				setUpCall(proHdlr, destNum, (short)1, (short)3);//140
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 12://Account->Check_My_Data_Bal
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Check_My_Data_Bal,(short)0,(short)Check_My_Data_Bal.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, by_SMS,(short)0,(short)by_SMS.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, by_USSD,(short)0,(short)by_USSD.length);
				phase = HandleNavigation((short)1, (short)0, MAIN_MENU,(short)120);//back, ok, abort, nextOffset
				break;
			case 121://Account->Check_My_Data_Bal->by_SMS
				destNum[0] = (byte)0x03;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x94;
				destNum[3] = (byte)0xF9;
				SendSMS(proHdlr, Bal, (short)0, (short)3, destNum, (short)4);//499
				phase = HandleNavigation((short)12, MAIN_MENU, MAIN_MENU);
				break;
			case 122://Account->Check_My_Data_Bal->by_USSD
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '4';
				byteArray1[2] = '9';
				byteArray1[3] = '9';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 13://Account->Top_Up_Account
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Top_Up_Account,(short)0,(short)Top_Up_Account.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, by_SMS,(short)0,(short)by_SMS.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, by_Calling_IVR,(short)0,(short)by_Calling_IVR.length);
				phase = HandleNavigation((short)1, (short)0, MAIN_MENU,(short)130);//back, ok, abort, nextOffset
				destNum[0] = (byte)0x03;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x31;
				destNum[3] = (byte)0xF0;
				break;
			case 131://Account->Top_Up_Account->by_SMS
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x00,(byte)0x04, Enter_the_serial_number,(short)0,(short)Enter_the_serial_number.length,(short)1,(short)30);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)0);
					SendSMS(proHdlr, byteArray1, (short)0, (short)input_len, destNum, (short)4);//130
					phase = HandleNavigation((short)131, MAIN_MENU, MAIN_MENU);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=13;
				}
				break;
			case 133://Account->Top_Up_Account->by_Calling_IVR
				setUpCall(proHdlr, destNum, (short)1, (short)3);//130
				phase = HandleNavigation((short)13, MAIN_MENU, MAIN_MENU);
				break;
			case 14://Account->Transfer_Credit
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Transfer_Credit,(short)0,(short)Transfer_Credit.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, by_SMS,(short)0,(short)by_SMS.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, by_USSD,(short)0,(short)by_USSD.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, by_Calling_IVR,(short)0,(short)by_Calling_IVR.length);
				phase = HandleNavigation((short)1, (short)0, MAIN_MENU,(short)140);//back, ok, abort, nextOffset
				break;
			case 141://Account->Transfer_Credit->by_SMS->GI1
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x00,(byte)0x04, Send_Leones_amount,(short)0,(short)Send_Leones_amount.length,(short)1,(short)30);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)0);
					byteArray1[input_len]='#';
					phase = 1411;
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=14;
				}
				break;
			case 1411://Account->Transfer_Credit->by_SMS->GI1
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x00,(byte)0x04, Enter_the_Target_Number,(short)0,(short)Enter_the_Target_Number.length,(short)1,(short)30);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)(input_len+1));
					destNum[0] = (byte)0x03;
					destNum[1] = (byte)0x81;
					destNum[2] = (byte)0x21;
					destNum[3] = (byte)0xF1;
					SendSMS(proHdlr, byteArray1, (short)0, (short)input_len, destNum, (short)4);//121
					phase = HandleNavigation((short)141, MAIN_MENU, MAIN_MENU);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=141;
				}
				break;
			case 142://Account->Transfer_Credit->by_USSD
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '1';
				byteArray1[2] = '2';
				byteArray1[3] = '1';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 143://Account->Transfer_Credit->by_Calling_IVR
				destNum[0] = (byte)0x03;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x81;
				destNum[3] = (byte)0xF0;
				setUpCall(proHdlr, destNum, (short)1, (short)3);//180
				phase = HandleNavigation((short)14, MAIN_MENU, MAIN_MENU);
				break;
			case 15://Account->Call_Me
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Call_Me,(short)0,(short)Call_Me.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Whats_Call_Me,(short)0,(short)Whats_Call_Me.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Use_Call_Me,(short)0,(short)Use_Call_Me.length);
				phase = HandleNavigation((short)1, (short)0, MAIN_MENU,(short)150);//back, ok, abort, nextOffset
				break;
			case 151://Account->Call_Me->Whats_Call_Me
				phase = 15;
				print(Service_Please_call_me, (short)0, (short)Service_Please_call_me.length);
				break;
			case 152://Account->Call_Me->Use_Call_Me
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x00,(byte)0x04, Enter_the_Target_Number,(short)0,(short)Enter_the_Target_Number.length,(short)1,(short)30);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)0);
					destNum[0] = (byte)0x03;
					destNum[1] = (byte)0x81;
					destNum[2] = (byte)0x81;
					destNum[3] = (byte)0xF0;
					SendSMS(proHdlr, byteArray1, (short)0, (short)input_len, destNum, (short)4);
					phase = HandleNavigation((short)152, MAIN_MENU, MAIN_MENU);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=15;
				}
				break;
			case 16://Account->Call_Me
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Collect_Call,(short)0,(short)Collect_Call.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Whats_Collect_Call,(short)0,(short)Whats_Collect_Call.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Use_Collect_Call,(short)0,(short)Use_Collect_Call.length);
				phase = HandleNavigation((short)1, (short)0, MAIN_MENU,(short)160);//back, ok, abort, nextOffset
				break;
			case 161://Account->Collect_Call->Whats_Collect_Call
				phase = 16;
				print(Called_party_is_charged, (short)0, (short)Called_party_is_charged.length);
				break;
			case 162://Account->Collect_Call->Use_Collect_Call
				destNum[0] = (byte)0x03;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x81;
				destNum[3] = (byte)0xF2;
				setUpCall(proHdlr, destNum, (short)1, (short)3);//182
				phase = HandleNavigation((short)16, MAIN_MENU, MAIN_MENU);
				break;
			case 17://Account->Send_Me_Credits->Enter_the_Target_Number
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x00,(byte)0x04, Enter_the_Target_Number,(short)0,(short)Enter_the_Target_Number.length,(short)1,(short)30);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)0);
					byteArray1[input_len]='#';
					phase=171;
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=1;
				}
				break;
			case 171://Account->LENme_Credit->Enter_the_Target_Number->Maintain_negative_credits
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x00,(byte)0x04, Request_Leones_amount,(short)0,(short)Request_Leones_amount.length,(short)1,(short)30);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)(input_len+1));
					destNum[0] = (byte)0x04;
					destNum[1] = (byte)0x81;
					destNum[2] = (byte)0x21;
					destNum[3] = (byte)0x21;
					SendSMS(proHdlr, byteArray1, (short)0, (short)input_len, destNum, (short)4);//1212
					phase = HandleNavigation((short)17, MAIN_MENU, MAIN_MENU);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=17;
				}
				break;
			case 18://Account->LENme_Credit
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), LENme_Credit,(short)0,(short)LENme_Credit.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Whats_LENme_Credit,(short)0,(short)Whats_LENme_Credit.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Subscribe,(short)0,(short)Subscribe.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, Unsubscribe,(short)0,(short)Unsubscribe.length);
				phase = HandleNavigation((short)1, (short)MAIN_MENU, MAIN_MENU,(short)180);//back, ok, abort, nextOffset
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x07;
				destNum[3] = (byte)0x70;
				break;
			case 181://Account->LENme_Credit->Whats_LENme_Credit
				phase = 18;
				print(Maintain_negative_credits, (short)0, (short)Maintain_negative_credits.length);
				break;
			case 182://Account->LENme_Credit->Subscribe
				SendSMS(proHdlr, sub, (short)0, (short)sub.length, destNum, (short)4);//7007
				phase = HandleNavigation((short)18, MAIN_MENU, MAIN_MENU);
				break;
			case 183://Account->LENme_Credit->Unsubscribe
				SendSMS(proHdlr, unsub, (short)0, (short)unsub.length, destNum, (short)4);//7007
				phase = HandleNavigation((short)18, MAIN_MENU, MAIN_MENU);
				break;
			case 19://Account->Maintain_your_Line
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Maintain_your_Line,(short)0,(short)Maintain_your_Line.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Whats_maintain_your_Line,(short)0,(short)Whats_maintain_your_Line.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Subscribe,(short)0,(short)Subscribe.length);
				phase = HandleNavigation((short)1, (short)MAIN_MENU, MAIN_MENU,(short)190);//back, ok, abort, nextOffset
				break;
			case 191://Account->Maintain_your_Line->Whats_maintain_your_Line
				phase = 19;
				print(Maintain_num_for_a_period, (short)0, (short)Maintain_num_for_a_period.length);
				break;
			case 192://Account->Maintain_your_Line->Subscribe
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '7';
				byteArray1[2] = '8';
				byteArray1[3] = '9';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 20://Account->Check_Your_number
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '9';
				byteArray1[2] = '8';
				byteArray1[3] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)4);
				break;
			case 21://Account->Customer_Care
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Customer_Care,(short)0,(short)Customer_Care.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Call_Customer_Care,(short)0,(short)Call_Customer_Care.length);
				phase = HandleNavigation((short)1, (short)MAIN_MENU, MAIN_MENU,(short)210);//back, ok, abort, nextOffset
				break;
			case 211://Account->Customer_Care->Call_Customer_Care
				destNum[0] = (byte)0x03;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x11;
				destNum[3] = (byte)0xF1;
				setUpCall(proHdlr, destNum, (short)1, (short)3);//111
				phase = HandleNavigation((short)MAIN_MENU, MAIN_MENU, MAIN_MENU);
				break;
			case 2://Africell_Services
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Africell_Services,(short)0,(short)Africell_Services.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Africell_Data_Bundles,(short)0,(short)Africell_Data_Bundles.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Africell_Ya_Mix_Bundles,(short)0,(short)Africell_Ya_Mix_Bundles.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, Africell_SMB,(short)0,(short)Africell_SMB.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)4, SMS_Bundles,(short)0,(short)SMS_Bundles.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)5, Roaming,(short)0,(short)Roaming.length);
				phase = HandleNavigation((short)MAIN_MENU, (short)MAIN_MENU, MAIN_MENU,(short)22);//back, ok, abort, nextOffset
				break;
			case 23://Africell_Services->Africell_Data_Bundles
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '4';
				byteArray1[2] = '9';
				byteArray1[3] = '9';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 24://Africell_Services->Africell_Ya_Mix_Bundles
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '9';
				byteArray1[2] = '2';
				byteArray1[3] = '9';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 25://Africell_Services->Africell_SMB
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '4';
				byteArray1[2] = '9';
				byteArray1[3] = '9';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 26://Africell_Services->SMS_Bundles
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), SMS_Bundles,(short)0,(short)SMS_Bundles.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Daily_SMS_Bundle,(short)0,(short)Daily_SMS_Bundle.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Monthly_SMS_Bundle,(short)0,(short)Monthly_SMS_Bundle.length);
				phase = HandleNavigation((short)MAIN_MENU, (short)MAIN_MENU, MAIN_MENU,(short)260);//back, ok, abort, nextOffset
				break;
			case 261://Africell_Services->SMS_Bundles->Daily_SMS_Bundle
				print(Purchase_5_local_SMS, (short)0, (short)Purchase_5_local_SMS.length);
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x25;
				destNum[3] = (byte)0xF5;
				SendSMS(proHdlr, Sub, (short)0, (short)Sub.length, destNum, (short)4);//525
				phase = HandleNavigation((short)4, MAIN_MENU, MAIN_MENU);
				break;
			case 262://Africell_Services->SMS_Bundles->Monthly_SMS_Bundle
				print(Purchase_30_daily_SMS, (short)0, (short)Purchase_30_daily_SMS.length);
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x25;
				destNum[3] = (byte)0xF9;
				SendSMS(proHdlr, Sub, (short)0, (short)Sub.length, destNum, (short)4);//529
				phase = HandleNavigation((short)4, MAIN_MENU, MAIN_MENU);
				break;
			case 27://Africell_Services->Roaming
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Roaming,(short)0,(short)Roaming.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, What_is_Roaming,(short)0,(short)What_is_Roaming.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Activate_Prepaid_Roaming,(short)0,(short)Activate_Prepaid_Roaming.length);
				phase = HandleNavigation((short)MAIN_MENU, (short)MAIN_MENU, MAIN_MENU,(short)270);//back, ok, abort, nextOffset
				break;
			case 271://Africell_Services->Roaming->What_is_Roaming
				phase = 27;
				print(Roaming_outside_S_Leone, (short)0, (short)Roaming_outside_S_Leone.length);
				break;
			case 272://Africell_Services->Roaming->Activate_Prepaid_Roaming
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '2';
				byteArray1[2] = '3';
				byteArray1[3] = '5';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 3://Afrimoney
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '1';
				byteArray1[2] = '6';
				byteArray1[3] = '1';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 4://Entertainment
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Entertainment,(short)0,(short)Entertainment.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Fun_Ring_Tone,(short)0,(short)Fun_Ring_Tone.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Song_for_You,(short)0,(short)Song_for_You.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, Live_Score,(short)0,(short)Live_Score.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)4, AYV_News,(short)0,(short)AYV_News.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)5, Biblical_Quotes,(short)0,(short)Biblical_Quotes.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)6, Quran_Recitation,(short)0,(short)Quran_Recitation.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)7, Love_Meter,(short)0,(short)Love_Meter.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)8, Star_Sign,(short)0,(short)Star_Sign.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)9, Name_Analyzer,(short)0,(short)Name_Analyzer.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)10, Daily_Horoscope,(short)0,(short)Daily_Horoscope.length);
				phase = HandleNavigation((short)MAIN_MENU, (short)MAIN_MENU, MAIN_MENU,(short)40);//back, ok, abort, nextOffset
				break;
			case 41://Entertainment->Fun_Ring_Tone
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Fun_Ring_Tone,(short)0,(short)Fun_Ring_Tone.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Subscribe,(short)0,(short)Subscribe.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Unsubscribe,(short)0,(short)Unsubscribe.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, Call_RBT_Agent,(short)0,(short)Call_RBT_Agent.length);
				phase = HandleNavigation((short)4, (short)MAIN_MENU, MAIN_MENU,(short)410);//back, ok, abort, nextOffset
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x44;
				destNum[3] = (byte)0xF4;
				break;
			case 411://Entertainment->Fun_Ring_Tone->Subscribe
				setUpCall(proHdlr, destNum, (short)1, (short)3);//444
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 412://Entertainment->Fun_Ring_Tone->Unsubscribe
				SendSMS(proHdlr, unsub, (short)0, (short)unsub.length, destNum, (short)4);//444
				phase = HandleNavigation((short)41, MAIN_MENU, MAIN_MENU);
				break;
			case 413://Entertainment->Fun_Ring_Tone->Call_RBT_Agent
				destNum[3] = (byte)0xF3;
				setUpCall(proHdlr, destNum, (short)1, (short)3);//443
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 42://Entertainment->Song_for_You
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Song_for_You,(short)0,(short)Song_for_You.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Send_a_song_to_someone,(short)0,(short)Send_a_song_to_someone.length);
				phase = HandleNavigation((short)4, (short)MAIN_MENU, MAIN_MENU,(short)420);//back, ok, abort, nextOffset
				break;
			case 421://Entertainment->Song_for_You->Send_a_song_to_someone
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '8';
				byteArray1[2] = '8';
				byteArray1[3] = '8';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 43://Entertainment->Live_Score
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Live_Score,(short)0,(short)Live_Score.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Get_match_reminders,(short)0,(short)Get_match_reminders.length);
				phase = HandleNavigation((short)4, (short)MAIN_MENU, MAIN_MENU,(short)430);//back, ok, abort, nextOffset
				break;
			case 431://Entertainment->Live_Score->Get_match_reminders
				phase = MAIN_MENU;
				byteArray1[0] = '*';
				byteArray1[1] = '7';
				byteArray1[2] = '0';
				byteArray1[3] = '7';
				byteArray1[4] = '#';
				SendUSSD(proHdlr,rspHdlr,byteArray1,(short)0,(short)5);
				break;
			case 44://Entertainment->AYV_News
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), AYV_News,(short)0,(short)AYV_News.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, AYV_News_update,(short)0,(short)AYV_News_update.length);
				phase = HandleNavigation((short)4, (short)MAIN_MENU, MAIN_MENU,(short)440);//back, ok, abort, nextOffset
				break;
			case 441://Entertainment->AYV_News->AYV_News_update
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x92;
				destNum[3] = (byte)0xF8;
				SendSMS(proHdlr, start, (short)0, (short)start.length, destNum, (short)4);//298
				phase = HandleNavigation((short)4, MAIN_MENU, MAIN_MENU);
				break;
			case 45://Entertainment->Biblical_Quotes
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Biblical_Quotes,(short)0,(short)Biblical_Quotes.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Subs_Daily_Bible_Quotes,(short)0,(short)Subs_Daily_Bible_Quotes.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Unsubscribe,(short)0,(short)Unsubscribe.length);
				phase = HandleNavigation((short)4, (short)MAIN_MENU, MAIN_MENU,(short)450);//back, ok, abort, nextOffset
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x27;
				destNum[3] = (byte)0x02;
				break;
			case 451://Entertainment->Biblical_Quotes->Subs_Daily_Bible_Quotes
				SendSMS(proHdlr, sub, (short)0, (short)sub.length, destNum, (short)4);//7220
				phase = HandleNavigation((short)4, MAIN_MENU, MAIN_MENU);
				break;
			case 452://Entertainment->Biblical_Quotes->Unsubscribe
				SendSMS(proHdlr, unsub, (short)0, (short)unsub.length, destNum, (short)4);//7220
				phase = HandleNavigation((short)4, MAIN_MENU, MAIN_MENU);
				break;
			case 46://Entertainment->Quran_Recitation
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Quran_Recitation,(short)0,(short)Quran_Recitation.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Quranic_recitation_of_your_choice,(short)0,(short)Quranic_recitation_of_your_choice.length);
				phase = HandleNavigation((short)4, (short)MAIN_MENU, MAIN_MENU,(short)460);//back, ok, abort, nextOffsetbreak;
				break;
			case 461://Entertainment->Quran_Recitation->Quranic_recitation_of_your_choice
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x17;
				destNum[3] = (byte)0x00;
				setUpCall(proHdlr, destNum, (short)1, (short)3);//7100
				phase = HandleNavigation((short)4, MAIN_MENU, MAIN_MENU);
				break;
			case 47://Entertainment->Love_Meter->Enter_your_name
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x01,(byte)0x04, Enter_your_name,(short)0,(short)Enter_your_name.length,(short)1,(short)60);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)0);
					byteArray1[input_len]='+';
					phase=471;
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=4;
				}
				break;
			case 471://Entertainment->Love_Meter->Enter_your_name->Enter_your_friend_name
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x01,(byte)0x04, Enter_your_friend_name,(short)0,(short)Enter_your_friend_name.length,(short)1,(short)60);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)(input_len+1));
					destNum[0] = (byte)0x04;
					destNum[1] = (byte)0x81;
					destNum[2] = (byte)0x55;
					destNum[3] = (byte)0xF5;
					SendSMS(proHdlr, byteArray1, (short)0, (short)input_len, destNum, (short)4);//555
					phase = HandleNavigation((short)47, MAIN_MENU, MAIN_MENU);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=47;
				}
				break;
			case 48://Entertainment->Star_Sign->Enter_your_sign
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x01,(byte)0x04, Enter_your_sign,(short)0,(short)Enter_your_sign.length,(short)1,(short)60);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)0);
					byteArray1[input_len]='+';
					phase=481;
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=4;
				}
				break;
			case 481://Entertainment->Love_Meter->Enter_your_sign->Enter_your_friend_s_Sign
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x01,(byte)0x04, Enter_your_friend_s_Sign,(short)0,(short)Enter_your_friend_s_Sign.length,(short)1,(short)60);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)(input_len+1));
					destNum[0] = (byte)0x04;
					destNum[1] = (byte)0x81;
					destNum[2] = (byte)0x41;
					destNum[3] = (byte)0x41;
					SendSMS(proHdlr, byteArray1, (short)0, (short)input_len, destNum, (short)4);
					phase = HandleNavigation((short)48, MAIN_MENU, MAIN_MENU);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=48;
				}
				break;
			case 49://Entertainment->Name_Analyzer
				phase=MAIN_MENU;
				proHdlr.initGetInput((byte)0x01,(byte)0x04, Enter_your_name,(short)0,(short)Enter_your_name.length,(short)1,(short)60);//alpha 01, numeric 00
				//insert default text 
				//proHdlr.appendTLV((byte)0x97,(byte)0x04, gi311_defTxt,(short)0,(short)gi311_defTxt.length);
				result=proHdlr.send();
				if(result == RES_CMD_PERF){
					input_len = rspHdlr.copyTextString( byteArray1, (short)0);
					destNum[0] = (byte)0x04;
					destNum[1] = (byte)0x81;
					destNum[2] = (byte)0x99;
					destNum[3] = (byte)0xF9;
					SendSMS(proHdlr, byteArray1, (short)0, (short)input_len, destNum, (short)4);//999
					phase = HandleNavigation((short)4, MAIN_MENU, MAIN_MENU);
				}
				else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
					phase=4;
				}
				break;
			case 50://Entertainment->Daily_Horoscope
				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Daily_Horoscope,(short)0,(short)Daily_Horoscope.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, Get_astrological_meaning,(short)0,(short)Get_astrological_meaning.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, Subscribe,(short)0,(short)Subscribe.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, Unsubscribe,(short)0,(short)Unsubscribe.length);
				phase = HandleNavigation((short)4, (short)MAIN_MENU, MAIN_MENU,(short)500);//back, ok, abort, nextOffset
				break;
			case 501://Entertainment->Daily_Horoscope->Get_astrological_meaning
				phase = 50;
				break;
			case 502://Entertainment->Daily_Horoscope->Subscribe
				// index = 0, Length = 5
				byteArray1[0]='A';byteArray1[1]='r';byteArray1[2]='i';byteArray1[3]='e';byteArray1[4]='s';
				// index = 5, Length = 6
				byteArray1[5]='T';byteArray1[6]='a';byteArray1[7]='u';byteArray1[8]='r';byteArray1[9]='u';byteArray1[10]='s';
				// index = 11, Length = 6
				byteArray1[11]='G';byteArray1[12]='e';byteArray1[13]='m';byteArray1[14]='i';byteArray1[15]='n';byteArray1[16]='i';
				// index = 17, Length = 6
				byteArray1[17]='C';byteArray1[18]='a';byteArray1[19]='n';byteArray1[20]='c';byteArray1[21]='e';byteArray1[22]='r';
				// index = 23, Length = 3
				byteArray1[23]='L';byteArray1[24]='e';byteArray1[25]='o';
				// index = 26, Length = 5
				byteArray1[26]='V';byteArray1[27]='i';byteArray1[28]='r';byteArray1[29]='g';byteArray1[30]='o';
				// index = 31, Length = 5
				byteArray1[31]='L';byteArray1[32]='i';byteArray1[33]='b';byteArray1[34]='r';byteArray1[35]='a';
				// index = 36, Length = 7
				byteArray1[36]='S';byteArray1[37]='c';byteArray1[38]='o';byteArray1[39]='r';byteArray1[40]='p';byteArray1[41]='i';byteArray1[42]='o';
				// index = 43, Length = 11
				byteArray1[43]='S';byteArray1[44]='a';byteArray1[45]='g';byteArray1[46]='i';byteArray1[47]='t';byteArray1[48]='t';byteArray1[49]='a';byteArray1[50]='r';byteArray1[51]='i';byteArray1[52]='u';byteArray1[53]='s';
				// index = 54, Length = 9
				byteArray1[54]='C';byteArray1[55]='a';byteArray1[56]='p';byteArray1[57]='r';byteArray1[58]='i';byteArray1[59]='c';byteArray1[60]='o';byteArray1[61]='r';byteArray1[62]='n';
				// index = 63, Length = 8
				byteArray1[63]='A';byteArray1[64]='q';byteArray1[65]='u';byteArray1[66]='a';byteArray1[67]='r';byteArray1[68]='i';byteArray1[69]='u';byteArray1[70]='s';
				// index = 71, Length = 6
				byteArray1[71]='P';byteArray1[72]='i';byteArray1[73]='s';byteArray1[74]='c';byteArray1[75]='e';byteArray1[76]='s';

				proHdlr.init(PRO_CMD_SELECT_ITEM,(byte)0x01,DEV_ID_ME);
				proHdlr.appendTLV((byte) (TAG_ALPHA_IDENTIFIER), Subscribe,(short)0,(short)Subscribe.length);
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)1, byteArray1,(short)0,(short)5);//Aries
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)2, byteArray1,(short)5,(short)6);//Taurus
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)3, byteArray1,(short)11,(short)6);//Gemini
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)4, byteArray1,(short)17,(short)6);//Cancer
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)5, byteArray1,(short)23,(short)3);//Leo
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)6, byteArray1,(short)26,(short)5);//Virgo
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)7, byteArray1,(short)31,(short)5);//Libra
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)8, byteArray1,(short)36,(short)7);//Scorpio
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)9, byteArray1,(short)43,(short)11);//Sagittarius
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)10, byteArray1,(short)54,(short)9);//Capricorn
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)11, byteArray1,(short)63,(short)8);//Aquarius
				proHdlr.appendTLV((byte) (TAG_ITEM),(byte)12, byteArray1,(short)71,(short)6);//Pisces
				phase = HandleNavigation((short)50, (short)MAIN_MENU, MAIN_MENU,(short)5020);//back, ok, abort, nextOffset
				destNum[0] = (byte)0x04;
				destNum[1] = (byte)0x81;
				destNum[2] = (byte)0x88;
				destNum[3] = (byte)0xF8;
				break;
			case 5021://Entertainment->Daily_Horoscope->Aries
				byteArray1[0]='a';
				SendSMS(proHdlr, byteArray1, (short)0, (short)5, destNum, (short)4);//Aries
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5022://Entertainment->Daily_Horoscope->Taurus
				byteArray1[5]='t';
				SendSMS(proHdlr, byteArray1, (short)5, (short)6, destNum, (short)4);//Taurus
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5023://Entertainment->Daily_Horoscope->Gemini
				byteArray1[11]='g';
				SendSMS(proHdlr, byteArray1, (short)11, (short)6, destNum, (short)4);//Gemini
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5024://Entertainment->Daily_Horoscope->Cancer
				byteArray1[17]='c';
				SendSMS(proHdlr, byteArray1, (short)17, (short)6, destNum, (short)4);//Cancer
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5025://Entertainment->Daily_Horoscope->Leo
				byteArray1[23]='l';
				SendSMS(proHdlr, byteArray1, (short)23, (short)3, destNum, (short)4);//Leo
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5026://Entertainment->Daily_Horoscope->Virgo
				byteArray1[26]='v';
				SendSMS(proHdlr, byteArray1, (short)26, (short)5, destNum, (short)4);//Virgo
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5027://Entertainment->Daily_Horoscope->Libra
				byteArray1[31]='l';
				SendSMS(proHdlr, byteArray1, (short)31, (short)5, destNum, (short)4);//Libra
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5028://Entertainment->Daily_Horoscope->Scorpio
				byteArray1[36]='s';
				SendSMS(proHdlr, byteArray1, (short)36, (short)7, destNum, (short)4);//Scorpio
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5029://Entertainment->Daily_Horoscope->Sagittarius
				byteArray1[43]='s';
				SendSMS(proHdlr, byteArray1, (short)43, (short)11, destNum, (short)4);//Sagittarius
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5030://Entertainment->Daily_Horoscope->Capricorn
				byteArray1[54]='c';
				SendSMS(proHdlr, byteArray1, (short)54, (short)9, destNum, (short)4);//Capricorn
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5031://Entertainment->Daily_Horoscope->Aquarius
				byteArray1[63]='a';
				SendSMS(proHdlr, byteArray1, (short)63, (short)8, destNum, (short)4);//Aquarius
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 5032://Entertainment->Daily_Horoscope->Pisces
				byteArray1[71]='p';
				SendSMS(proHdlr, byteArray1, (short)71, (short)6, destNum, (short)4);//Pisces
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
				break;
			case 503://Entertainment->Daily_Horoscope->Unsubscribe
				SendSMS(proHdlr, Unsub, (short)0, (short)Unsub.length, destNum, (short)4);
				phase = HandleNavigation((short)11, MAIN_MENU, MAIN_MENU);
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
	
	public short HandleNavigation(short back, short ok, short abort, short nextOffset){
		ProactiveHandler proHdlr = ProactiveHandler.getTheHandler();
		ProactiveResponseHandler rspHdlr = ProactiveResponseHandler.getTheHandler();
		short result = proHdlr.send(); 
		if(result == RES_CMD_PERF){
			return (short)(nextOffset+rspHdlr.getItemIdentifier());
		}else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
			return (short)back;
		}
		return (short)abort;
	}
	
	public short HandleNavigation(short back, short ok, short abort){
		ProactiveHandler proHdlr = ProactiveHandler.getTheHandler();
		short result = proHdlr.send(); 
		if(result == RES_CMD_PERF){
			return (short)ok;
		}else if(result == RES_CMD_PERF_BACKWARD_MOVE_REQ){
			return (short)back;
		}
		return (short)abort;
	}

	//setup call 
	private void setUpCall(ProactiveHandler proHdlr, byte[] caddress, short callOffset, short calladdrlen) 
	{
		proHdlr.init(PRO_CMD_SET_UP_CALL, (byte) 0x00, DEV_ID_NETWORK);
		proHdlr.appendTLV(TAG_ADDRESS, caddress, (short) callOffset, (short) calladdrlen);
		proHdlr.appendTLV(TAG_ALPHA_IDENTIFIER, Calling, (short) 0, (short) Calling.length);
		//proHdlr.send();
		//return;
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

	//By Default Param1 is the Call Address, Param2 is the Message
	public final void SendSMS(ProactiveHandler proHdlr,byte[] msg, short msgoffset, short msglen,byte[] dest, short dest_len){
		short i;
		//byte result;
		proHdlr.init((byte)0x13,(byte)0x01,DEV_ID_NETWORK);//PRO_CMD_SEND_SHORT_MESSAGE = 0x13
		//Qualifier 0x01 means that the data requires Packing by the ME
		/*byteArray1[(short)0]=(byte)'R';
		byteArray1[(short)1]=(byte)'e';
		byteArray1[(short)2]=(byte)'q';
		byteArray1[(short)3]=(byte)'u';
		byteArray1[(short)4]=(byte)'e';
		byteArray1[(short)5]=(byte)'s';
		byteArray1[(short)6]=(byte)'t';
		byteArray1[(short)7]=(byte)'i';
		byteArray1[(short)8]=(byte)'n';
		byteArray1[(short)9]=(byte)'g';

		//Alpha identifier
		proHdlr.appendTLV((byte)0x05,byteArray1,(short)0,(short)10);*/

		//Build SMS TPDU

		//MESSAGE TYPE INDICATOR (MTI) bits 0-1 = SMS SUBMIT MS to SC
		//REJECT DUPLICATES (RD) bit 2 = ACCEPT DUPLICATES
		//VALIDITY PERIOD FORMAT (VPF) bits 3-4=  TP-VP field present - relative format
		//Status-Report-Indication (SRR) bit 5 - No Report
		//USER DATA HEADER INDICATOR (UDHI) = No header
		//REPLY PATH (RP) - Not Set
		byteArray2[(short)0]=(byte)0x11;
		//MESSAGE REFERENCE (MR)
		byteArray2[(short)1]=(byte)0x00;

		//DESTINATION ADDRESS (DA)- LENGTH
		//USER DATA
		for (i=(short)0;i<dest_len;i++) 
			byteArray2[(short)(2+i)]=(byte)dest[i];

		//PROTOCOL IDENTIFIER (PID)
		byteArray2[(short)(2+dest_len)]=(byte)0x00;

		//DATA CODING SCHEME (DCS)
		byteArray2[(short)(3+dest_len)]=(byte)0x04;

		//VALIDITY PERIOD (VP) =  1 week
		byteArray2[(short)(4+dest_len)]=(byte)0xFF;

		//USER DATA LENGTH (UDL) and USER DATA
		//LEN
		byteArray2[(short)(5+dest_len)]=(byte)msglen;

		//USER DATA
		for (i=(short)0;i<msglen;i++) 
			byteArray2[(short)((short)(6+dest_len+i))]=(byte)msg[(short)(msgoffset+(short)i)];
		i=(short)((short)(6+dest_len) + msglen);

		//Append SMS TPDU
		proHdlr.appendTLV((byte)0x0B,byteArray2,(short)0,i);

		//return proHdlr.send();
		/*result = proHdlr.send();
		if ((result >= 0x01) && (result <= 0x3F))
		{
			HandleError(proHdlr,result);
		}*/
	}

	public void print(byte[] arr,short off, short len){
		ProactiveHandler proHdlr = ProactiveHandler.getTheHandler();//command 
		proHdlr.initDisplayText((byte)0x81, (byte)0x04, arr, (short)off, (short)len);//01: clear after delay/HP, 81: user input/HP
		proHdlr.send();
	}
}
