# Fernfertilizer
Fernfertilizer is an analytical automatic deobfuscator for Java's obfuscated programs. 


More intelligent by far than any other deobfuscator, this program intelligently scans for constants and generates a percentage and ratio
of similarities and decides whether the class is identified as the mapping.

## Usage
```
Option (* = required)                 Description                           
---------------------                 -----------                           
* -1, -f, -o, --first, --original     Sets the ORIGINAL (usually deobf) JAR.
  <File>                                                                    
* -2, -n, -s, --new, --second <File>  Sets the SECONDARY (usually obf       
                                        updated) JAR.                       
-?, -h, --help                        Launches the help menu.               
-d, --debug                           Launches the DebugComparator.         
-l, --log <File>                      Logs statistics of a JAR.             
--passgrade, --pg <Integer>           Set the passing grade for mappings. 
```
Example: ```java -jar Fernfertilizier-1.0-SNAPSHOT.jar -1 minecraft-1.0.jar -2 minecraft-1.1.jar```