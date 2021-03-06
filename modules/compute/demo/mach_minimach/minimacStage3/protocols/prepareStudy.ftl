#MOLGENIS walltime=48:00:00 nodes=1 cores=1 mem=8

#FOREACH project,chr

getFile ${imputationToolJar}
getFile ${imputationToolJsciCoreJar}
getFile ${imputationToolGeneticaLibrariesJar}

getFile ${studyTriTyperChrDir}/GenotypeMatrix.dat
getFile ${studyTriTyperChrDir}/Individuals.txt
getFile ${studyTriTyperChrDir}/PhenotypeInformation.txt
getFile ${studyTriTyperChrDir}/SNPMappings.txt
getFile ${studyTriTyperChrDir}/SNPs.txt
getFile ${referenceTriTyperDir}/GenotypeMatrix.dat
getFile ${referenceTriTyperDir}/Individuals.txt
getFile ${referenceTriTyperDir}/PhenotypeInformation.txt
getFile ${referenceTriTyperDir}/SNPMappings.txt
getFile ${referenceTriTyperDir}/SNPs.txt

#inputs "${studyTriTyperChrDir}/GenotypeMatrix.dat"
#inputs "${studyTriTyperChrDir}/Individuals.txt"
#inputs "${studyTriTyperChrDir}/PhenotypeInformation.txt"
#inputs "${studyTriTyperChrDir}/SNPMappings.txt"
#inputs "${studyTriTyperChrDir}/SNPs.txt"
inputs "${referenceTriTyperDir}/GenotypeMatrix.dat"
inputs "${referenceTriTyperDir}/Individuals.txt"
inputs "${referenceTriTyperDir}/PhenotypeInformation.txt"
inputs "${referenceTriTyperDir}/SNPMappings.txt"
inputs "${referenceTriTyperDir}/SNPs.txt"
alloutputsexist "${preparedStudyDir}/chr${chr}.dat" \
"${preparedStudyDir}/chr${chr}.map" \
"${preparedStudyDir}/chr${chr}.markersbeagleformat" \
"${preparedStudyDir}/chr${chr}.ped" \
"${preparedStudyDir}/exportlog.txt"

#If preparedStudyDir already exists delete it
if [ -d ${preparedStudyDir} ]
then
	rm -r ${preparedStudyDir}
fi


module load jdk/${javaversion}

mkdir -p ${preparedStudyTempDir}


java -Xmx8g -jar ${imputationToolJar} \
--mode ttpmh \
--in ${studyTriTyperChrDir} \
--hap ${referenceTriTyperDir} \
--out ${preparedStudyTempDir}


#Get return code from last program call
returnCode=$?

if [ $returnCode -eq 0 ]
then
	
	echo -e "\nMoving temp files to final files\n\n"

	mv ${preparedStudyTempDir} ${preparedStudyDir}

    putFile ${preparedStudyDir}/chr${chr}.dat
    putFile ${preparedStudyDir}/chr${chr}.map
    putFile ${preparedStudyDir}/chr${chr}.markersbeagleformat
    putFile ${preparedStudyDir}/chr${chr}.ped
    putFile ${preparedStudyDir}/exportlog.txt
	
else
  
	echo -e "\nNon zero return code not making files final. Existing temp files are kept for debuging purposes\n\n"
	#Return non zero return code
	exit 1

fi