load("./log.js");




var card = new Card();

card.reset(Card.RESET_COLD);

var buf = new ByteBuffer();


print("// Select Applet");
card.lsc_selectApplet(APP_AID);


print("// reset logger")
card.lsc_sendApdu(0xB0,0x80,0x00,0x00);



card.lsc_sendApdu(0xB0,0x20,0x00,0x00);



card.lsc_sendApdu(0xB0,0x30,0x00,0x00);



card.lsc_sendApdu(0xB0,0x40,0x00,0x00);



print("// call getArray")
card.lsc_sendApdu(0xB0,0x80,0x02,0x00);
buf.clear();
while(card.SW1 == 0x9F){
	card.lsc_sendApdu(0xB0,0x80,0x02,0x00,card.SW2);
	buf.append(card.response);
}

parseLogAry(buf.toByteString());

//listLog(buf.toByteString());
