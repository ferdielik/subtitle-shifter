**Subtitle Shifter**
Manage subtitles
Only srt format for now.

*Usage*
<pre>java -jar sshifter.jar -f <subtitle-file> [-l|-s|-m|-h|-o|-a]
</pre>

*Example Usages*
<pre>
java -r sshifter.jar -f orj.srt -l10 -s20 -m1
# subtitle 1 minutes, 20 seconds and 10 milliseconds shifted
# new subtitle: resycn-orj.srt
</pre>
<pre>
java -r sshifter.jar -f orj.srt -l-35
# subtitle -35 milliseconds shifted
# subtitle overrided
</pre>

Commands
<pre>
usage: java -jar sshifter.jar -f <subtitle-file> [-l|-s|-m|-h|-o|-a]
     -a,--after <arg>           After time
     -e,--encoding <arg>        Subtitle encoding
     -f,--subtitle-file <arg>   Subtitle file
     -h,--Hours <arg>           Hours
     -l,--Milliseconds <arg>    Milliseconds
     -m,--Minutes <arg>         Minutes
     -o,--override              Override subtitle
     -s,--Seconds <arg>         Seconds
</pre>

