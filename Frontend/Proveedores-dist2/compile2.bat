call ng build --base-href "/dist2/" --prod
call xcopy "C:\javainstalls\FrontEnd-DIACOext\DIACOext\dist\DIACOext" "C:\javainstalls\glassfish4\glassfish\domains\domain1\docroot\dist2" /Y /S /H
