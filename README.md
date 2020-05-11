# Fernfertilizer
Fernfertilizer is an analytical automatic deobfuscator for Java's obfuscated programs. 


This program intelligently scans for constants and generates a percentage and ratio
of similarities and decides whether the class is identified as the mapping.

## Usage
```
Option (* = required)                Description
---------------------                -----------
* -1, -f, -o, --original <File>      Sets the ORIGINAL (deobf) JAR.
* -2, -n, -s, --new <File>           Sets the SECOND (obf) or updated JAR.
-?, -h, --help                       Launches the HELP menu.
-f, --field                          If field is added, field mapping will
                                       be enabled.
-g, -p, --grade, --passgrade, --     Sets the passing grade.
  percentage, --pg <Integer>
-m, --samemodifiers, --sm <Boolean>  Sets whether same modifiers rule apply
                                       to field comparison.
```
Example: ```java -jar Fernfertilizier-1.0-SNAPSHOT.jar -1 minecraft-1.0.jar -2 minecraft-1.1.jar```
