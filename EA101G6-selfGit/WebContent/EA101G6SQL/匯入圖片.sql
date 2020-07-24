DROP DIRECTORY MEDIA_DIR;
DROP FUNCTION load_blob;
CREATE OR REPLACE  DIRECTORY MEDIA_DIR AS 'C:/Users/Java/Desktop/EA101G6SQL/MALLIMG/';
/* 擷取檔案的FUNCTION */ 
CREATE OR REPLACE FUNCTION load_blob( myFileName VARCHAR) RETURN BLOB as result BLOB;  
  myBFILE      BFILE;
  myBLOB       BLOB;
BEGIN
    myBFILE := BFILENAME('MEDIA_DIR',myFileName);
    dbms_lob.createtemporary(myBLOB, TRUE);
    dbms_lob.fileopen(myBFILE,dbms_lob.file_readonly);
    dbms_lob.loadfromfile(myBLOB,myBFILE,dbms_lob.getlength(myBFILE) );
    dbms_lob.fileclose(myBFILE);
    RETURN myBLOB;
END load_blob;