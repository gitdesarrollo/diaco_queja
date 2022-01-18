call ng build --base-href "/dist/" --prod
call xcopy "C:\javainstalls\DIACOr\FrontEnd-DIACOint\dist" "C:\javainstalls\glassfish4\glassfish\domains\domain1\docroot\dist" /Y /S /H
