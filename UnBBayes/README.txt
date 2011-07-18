CONFIGURING MAVEN
------------------

The first thing needed is to install the maven project in your computer.

After that, it is necessary to install the maven plugin for your IDE, in this case, the Eclipse IDE.
The update site for this plugin is: http://q4e.googlecode.com/svn/trunk/updatesite/.

Before using this new Maven project, it is necessary to install the following libraries in
the local repository. These jars were not found in common remote repositories.

In the near future, these files shall be in unbbayes's remote project.

Go to \lib\jpf>
mvn install:install-file -DgroupId=guess -DartifactId=jpf -Dversion=1.5 -Dpackaging=jar -Dfile=jpf.jar

CREATING A RELEASE
-------------------

-> NEW RELEASE - ZIP FILE
1. Change the pom file so that it defines the right version (majorVersion.NumberOfFeatures.NumberOfFixedBugs).
2. Define the features implemented and bugs fixed in the RELEASE.txt file and in src/changes/changes.xml 
and the candidates features and bugs for the next version in README.txt. 
3. Change the unbbayes.properties file to the correct version
4. Create dist file. Go to the projects root folder command line and run*:
mvn assembly:assembly -Dmaven.test.skip=true
5. Make a few changes in the generated dist file. 
a) Unzip the distribution file somewhere. Remove unnecessary example files (until examples folder is cleaned up).
b) Rename the jar file to unbbayes.jar.
c) Zip the remain files again (without the '-dist' part).

-> NEW WEBSITE**
6. Make the necessary changes in the web site by changing the files in /src/site/.
7. Create the website. Go to the projects root folder command line and run**:
mvn site:site -Dmaven.test.skip=true
8. Verify that the created website is correct.
9. Since we do not know how to add direct html code using the apt format, we have to manually change the
generated video_tutorial.html file. Replace the code for showing the youtube video by copying from the .apt 
source file and pasting into the corresponding postion in the .html generated file. Also make sure to 
change the css file to make the left menu box big enough to fit the powered by images. To make this process 
easier, there are files already changed on src/site/changes folder.
10. Deploy the new website. Go to the projects root folder command line and run**:
mvn site:deploy -Dmaven.test.skip=true

* For now we are skipping the tests because we have to fix some tests before doing that.
** To make sure you can deploy the website, please configure your machine to be able to connect 
to the sourceforge server. Read http://docs.codehaus.org/display/MAVENUSER/MavenAndSourceforge 
for details.


CANDIDATES FOR NEXT RELEASE
---------------------------

--add--
 2955219	plugin dependency resolution	 Open	 2010-02-19	 nobody	 nobody	 5
 2927988	Create Conditional Probability Script	 Open	 2010-01-08	rommelnc	rommelnc	 7
 2925261	Implement japanese localization	 Open	 2010-01-03	cardialfly	cardialfly	 1
 2925260	Convert current modules and functionalities to plugins	 Open	 2010-01-03	cardialfly	cardialfly	 4
 2907019	Create an MSBN in SSBN construction	 Open	 2009-12-01	rommelnc	rommelnc	 7
 2906481	Load and Save XMLBIF 0.3 format	 Open	 2009-12-01	rommelnc	rommelnc	 5
 2810972	Marginalization	 Open	 2009-06-23	rommelnc	rommelnc	 8
 2718989	Improve performance	 Open	 2009-03-28	rommelnc	rommelnc	 9
 2717798	Implement test case for exact Evaluation	 Open	 2009-03-27	rommelnc	rommelnc	 5
 2027179	Do not allow duplicate finding	 Open	 2008-07-24	laecio_lima	rommelnc	 5
 1999629	Pseudocode to reference Context Nodes	 Open	 2008-06-21	 nobody	cardialfly	 4
 1968929	"Metafor" interfaces	 Open	 2008-05-21	cardialfly	cardialfly	 5
 1968926	Make it easier to edit networks at lower display resolutions	 Open	 2008-05-21	laecio_lima	cardialfly	 1
 1962669	Likelyhood finding	 Open	 2008-05-12	 nobody	cardialfly	 5
 1962667	FOL finding	 Open	 2008-05-12	 nobody	cardialfly	 2
 1962651	Save current	 Open	 2008-05-12	 nobody	cardialfly	 3
 1949085	Saving findings on PR-OWL files	 Open	 2008-04-22	 nobody	cardialfly	 5
 1908032	MEBN element's description and PR-OWL comments	 Open	 2008-03-05	laecio_lima	cardialfly	 1
 1908029	Dynamic help	 Open	 2008-03-05	laecio_lima	cardialfly	 1
 1907930	Saving "recursive" (entity ordering) information in PR-OWL	 Open	 2008-03-05	 nobody	cardialfly	 5
 1907925	Recursive variable definition in PR-OWL	 Open	 2008-03-05	adelfi23	cardialfly	 8
--fix--
 2963103	Blank error when editing context node formula	 Open	 2010-03-04	rommelnc	rommelnc	 None	 5
 2941126	OOBN nodes disappearing	 Open	 2010-01-27	rommelnc	rommelnc	 None	 7
 2941125	Duplicated nodes inside OOBN instance node	 Open	 2010-01-27	rommelnc	rommelnc	 None	 7
 2941119	Node popup menu item not working	 Open	 2010-01-27	rommelnc	rommelnc	 None	 6
 2941116	Nodes going to negative positions	 Open	 2010-01-27	rommelnc	rommelnc	 None	 5
 2941115	Problem moving OOBN instance node	 Open	 2010-01-27	rommelnc	rommelnc	 None	 5
 2941110	Moving private node outside instance node - OOBN	 Open	 2010-01-27	rommelnc	rommelnc	 None	 6
 2927933	Error while removing state	 Open	 2010-01-08	 nobody	rommelnc	 None	 8
 2925262	New GUI's popup menu localization/internationalization	 Open	 2010-01-03	 nobody	cardialfly	 None	 3
 2913292	Error messages while compiling a BN with several node types	 Open	 2009-12-12	 nobody	cardialfly	 None	 4
 2913272	Error compiling a MSBN with 1 section	 Open	 2009-12-12	 nobody	cardialfly	 None	 5
 2908370	Error while saving ubf file	 Open	 2009-12-03	laecio_lima	rommelnc	 None	 9
 1975377	Can not edit MTheory name	 Open	 2008-05-27	laecio_lima	rommelnc	 None	 1
 1955808	Problem with the names of the classes of Pr-OWL	 Open	 2008-05-01	 nobody	laecio_lima	 None	 5
 1949115	Cannot instantiate categorical entities	 Open	 2008-04-22	 nobody	cardialfly	 None	 3
 1910479	Some variable/entity names causes KB to crush	 Open	 2008-03-09	 nobody	cardialfly	 Later	 6
 1907281	Zombie-nodes at query panel	 Open	 2008-03-04	laecio_lima	cardialfly	 None	 1
 
 

