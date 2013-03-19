'''
Created on 09/03/2013

@author: Matthew
'''

veraibles = set()
lines = list()
errors = list()
totalErrors = 0


def main(name):
    filename = name.partition('.')
    if filename[3].equals("C"):
        with open(name, 'r') as fp:
            for line in iter(fp.readline, ''):
                print(line)
                lines.add(line)
                lineCheck(line) 
            
            
            
            
        
def lineCheck(line):
    if line.contains("if"):
        checkIf(line)
    elif line.contains(";") and line.contains("int") or line.contains("double") or line.contains("float") or line.contains("long"):
        declear(line)
    elif line.contains("\""):
        stringcheck(line)

def checkIf(line):
    error = 0
    if line.contains('=') and not (line.contains("==") or line.contains("=<") or line.contains("=>") or line.contains("!=")):
        errors.append("there is a bad if format please fix it has something to do with \"=\".\n")
        error = 1
        totalErrors = totalErrors+1
                
    if line.contains('&') and not (line.contains("&&")):
        errors.append("There is a a bad \"&&\" statement in this line\n")
        error = 1
        totalErrors = totalErrors+1
        
    if not line.count("(") == line.count(")"):
        errors.append("there is not a even amount of brackets \"(\" or \")\" is missing\n")
        error = 1
        totalErrors = totalErrors+1
    if line.contains("|") and not line.contains("||"):
        errors.append("your or statement is not correct\n")
        error = 1
        totalErrors = totalErrors+1
        
    if error == 1:
        errors.append(line+"\n")



def declear(line):
    error = 0
    piece = line.split()
    length = len(piece)
    if not line.contains(';'):
        errors.append("There is a decleartion with out \";\" in it\n")
        error =1
        totalErrors = totalErrors+1
    if line.contains(","):
        for i in range(length):
            if i > 1  and i < length:
                veraibles.add(piece[i])
    else :
        veraibles.add(piece[2])
        
    if error == 1:
        errors.append(line+"\n")
        
def stringcheck(line):
    pass