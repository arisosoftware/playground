# Vert.x Playground project #1
## Test for multi thread worker to act as bitcoin like compute solution

## test cluster

#2018 Dec 21 ,
## when reimport the project got error like this
<pre>
Description	Resource	Path	Location	Type
Execution default-resources of goal org.apache.maven.plugins:maven-resources-plugin:2.6:resources failed: Plugin org.apache.maven.plugins:maven-resources-plugin:2.6 or one of its dependencies could not be resolved: Failed to collect dependencies at org.apache.maven.plugins:maven-resources-plugin:jar:2.6 -> org.codehaus.plexus:plexus-utils:jar:2.0.5 (org.apache.maven.plugins:maven-resources-plugin:2.6:resources:default-resources:process-resources)
</pre>

#solution: 
<pre>
The following steps worked for me:

Close Eclipse.
Navigate to user home directory. (For example: "C:\Users\YourUserName\.m2")
Delete the "repository" folder.
Re-open Eclipse.
Click on the Maven project that has an issue and go to "Project" --> "Clean".
Right-click on the project and go to "Maven" --> "Update Project...".
Close Eclipse.
Open Eclipse.
Click on the project folder in the "Project Explorer" window (usually on the left).
Hit the F5 key a few times to Refresh your project.
Done!
</pre>