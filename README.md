**Subtitle Shifter**
Manage subtitles
Only srt format for now.

*Usage*
<pre>java -r sshifter.jar --files <subtitle-file> [-l|-s|-m|-h]
</pre>

*Example Usages*
<pre>
java -r sshifter.jar --files orj.srt -l10 -s20 -m1
# subtitle 1 minutes, 20 seconds and 10 milliseconds shifted
</pre>

Commands
<pre>
Usage: java -jar --files <subtitle-file> [-l|-s|-m|-h]
        --files                      Subtitle file
        --new-name                   New Subtitle name [optional]
    -l,                              Milliseconds for shift
    -s,                              Seconds for shift
    -m,                              Minutes for shift
    -h,                              Hours for shift
</pre>

