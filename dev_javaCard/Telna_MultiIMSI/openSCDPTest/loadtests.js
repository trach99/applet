/*
 *  ---------
 * |.##> <##.|  Open Smart Card Development Platform (www.openscdp.org)
 * |#       #|  
 * |#       #|  Copyright (c) 1999-2008 CardContact Software & System Consulting
 * |'##> <##'|  Andreas Schwier, 32429 Minden, Germany (www.cardcontact.de)
 *  --------- 
 *
 *  This file is part of OpenSCDP.
 *
 *  OpenSCDP is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License version 2 as
 *  published by the Free Software Foundation.
 *
 *  OpenSCDP is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSCDP; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

//
// Script to load test cases
//


load("tools/TestRunner.js");
load("tools/TestGroup.js");
load("tools/TestProcedure.js");


var param = new Array();

param["card"] = new Card();
param["crypto"] = new Crypto();



var testRunner = new TestRunner("Sample Test");
var currentDir = GPSystem.mapFilename("", GPSystem.CWD)
testRunner.setReportDirectory(currentDir);

testRunner.addTestProcedureFromXML("/testProcedure/tp_000_sample0.xml");
testRunner.addTestProcedureFromXML("/testProcedure/tp_001_sample1.xml");

testRunner.addTestGroupFromXML("/testGroup/tg_001_SampleTG.xml", param);




print("Test-Suite loaded...");
