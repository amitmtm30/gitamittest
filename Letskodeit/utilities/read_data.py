import csv
import xlrd

def getCSVData(fileName):
    #Create a empty list to store rows
    rows = []
    #Open the CSV file
    datafile = open(fileName, 'r')
    #Create a CSV Reader from CSV File
    csvreader = csv.reader(datafile)
    #Skip the headers
    fields = next(csvreader)
    #Add rows from reader to list
    for row in csvreader:
        rows.append(row)
    return rows

def getExcelData(filePath, sheetName):
    wb = xlrd.open_workbook(filePath)
    sheet = wb.sheet_by_name(sheetName)
    total_row = sheet.nrows
    rows = []
    for r in range(1, total_row):
        rows.append(sheet.row_values(r))
    return rows

def writeExcelData(filePath, sheetName, status):
    wb = xlrd.open_workbook(filePath)
    sheet = wb.sheet_by_name(sheetName)
    rows = sheet.nrows
    cols = sheet.ncols
    for st in status:
        count = 1
        sheet.write(1, cols-1, st)
        count +=1
    
    
        
    
    
    
    
