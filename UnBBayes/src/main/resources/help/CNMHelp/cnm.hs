<?xml version='1.0' encoding='ISO-8859-1'  ?>
<!DOCTYPE helpset
  PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
         "http://java.sun.com/products/javahelp/helpset_1_0.dtd">
<helpset version="1.0">

<!-- title -->
<title>CNM</title>

<!-- maps -->
<maps>
    <homeID>FirstTopic</homeID>
    <mapref location="cnm.jhm" />
</maps>

<!-- views -->
<view>
    <name>TOC</name>
    <label>Table Of Contents</label>
    <type>javax.help.TOCView</type>
    <data>cnmTOC.xml</data>
</view>

<view>
    <name>Search</name>
    <label>Search</label>
    <type>javax.help.SearchView</type>
    <data engine="com.sun.java.help.search.DefaultSearchEngine">
     JavaHelpSearch</data>
</view>

</helpset>
