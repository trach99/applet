var APP_AID = "A00000019490D7F2300200024F545000";
var XML_PATH = "/DynamicOTP/xmlFiles/test.xml";
TAG_PRINT =  0x01;
TAG_ASSERT= 0x40;
TAG_UNPACK =  0x50;

function readXml(fileName){
	var file = new File(fileName)
	var str = file.readAllAsString();
	str = str.replace(/^<\?xml\s+version\s*=\s*(["'])[^\1]+\1[^?]*\?>/, "");
	str = str.replace(/[\r\n]/g, "");
	var myIndex = new XML(str);
	return myIndex;
}

function updateAry(ary,id){
	if(ary[id] == undefined){
		ary[id] = 1;
	}else{
		ary[id] = ary[id] + 1;
	}
	return ary[id];
}






function listLog(logAry){
	print("\n\n// Parsing Logger Byte Array");
	var xmlOb = readXml(XML_PATH);
	var sourcePath = xmlOb.source;
	
	list = new TLVList(logAry, TLV.EMV);

	var i =0
	var tag,val;
	var tlv,strId;
	var xmlChild;


	
	srcFile = new File(sourcePath.toString());
	sourceFileAry = srcFile.readAllAsString().split("\r\n");
	var modLine;
	var pass = new Array();
	var passVal;
	for (; i < list.length; i++) {
		tlv = list.index(i);
		print(tlv.getTLV().toString(HEX));
		tag = tlv.getTag();
		// error in parsing length
		
		val = tlv.getValue();
		len = val.length;
		switch (tag) {
		case TAG_PRINT:
			strId = tlv.getValue().byteAt(0);
			xmlChild = xmlOb.log.(@id==strId.toString());
			xmlChild.@pass = "true";
			break;
		}
	}
	
	
	for each (var log in xmlOb.log){
		print("\n\n");		
		print("log: "+log.comment);
		print("at "+sourcePath+"#"+log.@line);
		if(log.@pass == undefined){
			print("covered: "+false);
		}else if(log.@pass == "true"){
			print("covered: true");
		}else{
			print("covered: false");
		}
		
	}
	
	print("\n\n");
}

function parseLogAry(logAry){
	print("\n\n// Parsing Logger Byte Array");
	var xmlOb = readXml(XML_PATH);
	var sourcePath = xmlOb.source;
	//print("sourcePath : "+sourcePath);
	
	list = new TLVList(logAry, TLV.EMV);

	var i =0
	var tag,val;
	var tlv,strId;
	var xmlChild;
	srcFile = new File(sourcePath.toString());
	sourceFileAry = srcFile.readAllAsString().split("\r\n");
	var modLine;
	var pass = new Array();
	var passVal;
	for (; i < list.length; i++) {
		tlv = list.index(i);
		print("\n\n===========================================");
		//print(tlv.getTLV().toString(HEX));
		tag = tlv.getTag();
		// error in parsing length
		
		val = tlv.getValue();
		len = val.length;
		switch (tag) {
		case TAG_PRINT:
			strId = tlv.getValue().byteAt(0);
			passVal = updateAry(pass,strId)
			print("id    : "+strId +"  pass :"+passVal);
			xmlChild = xmlOb.log.(@id==strId.toString());
			xmlChild.@pass = "true";
			
			
			switch (len) {
			case 0:
				break;
			
			case 1:
				
				break;
				
			case 2: // byte
			case 3: // short
				val = val.bytes(1,(len-1));
				break;
				
			default: // array
				print("Offset : "+val.bytes(1,2));
				print("Len    : "+(len-3));
				val = val.bytes(3,(len-3));
				break;
			}
			comment = xmlChild.comment;
			lineNum = xmlChild.@line;
			if(len == 1){
				print("log   : "+comment);
				print("at "+sourcePath+"#"+lineNum);
			}else if(len > 1){
				//print("Len: "+len)
				//print("// len2 : "+tlv.getL().toString(HEX))
				// remove id byte
				//val = val.bytes(1,(len-1));
				
				
				print("log    : "+comment+"\nvalue : "+val);
				print("at "+sourcePath+"#"+lineNum);
				modLine = sourceFileAry[parseInt(lineNum) -1].concat(" value: "+val)
				sourceFileAry[parseInt(lineNum) - 1]= modLine;
			}else{
				// error
			}
			break;

		default:
			break;
		}
	}
	newSrc = sourceFileAry.join("\r\n");	
}

