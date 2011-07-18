#!/bin/sh
java -jar -Xms128M -Xmx1024M -Djavax.xml.transform.TransformerFactory=unbbayes.util.XalanIndentNumberBugFixer unbbayes.jar

