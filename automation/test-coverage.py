#!/usr/bin/python

import csv

def calculated_test_coverage():
    with open(file="target/site/jacoco/jacoco.csv", mode="r") as file:
        csv_reader = csv.reader(file, delimiter=',') # makes csv easier to handle
        next(csv_reader) # skip titles on first line
        covered = 0
        missed = 0
        for lines in csv_reader: 
            missed += int(lines[3])
            covered += int(lines[4])
        return str(int(covered/(missed+covered)*100))+"%"

def add_test_coverage_to_readme():
    file_input = open(file="README.md", mode="r")
    readme_text = file_input.read()
    file_input.close()

    file_output = open(file="README.md", mode="w")
    file_output.write("Coverage: " + calculated_test_coverage() +"\n")
    file_output.write(readme_text)
    file_output.close()
    
def update_test_coverage_in_readme():
    file_input = open(file="README.md", mode="r")
    text = ""
    while True:
        line = file_input.readline()
        if line == "":
            break
   
        start_line = line.split(":")
        if start_line[0] == "Coverage":
            text += "Coverage: "+calculated_test_coverage()+"\n"
        else:
            text += line
    file_input.close()
    file_output = open(file="README.md", mode="w")
    file_output.write(text)
    file_output.close

def add_or_update():
    file_input = open(file="README.md", mode="r")
    while True:
        line = file_input.readline()
        start_line = line.split(":")
        if start_line[0] == "Coverage":
            update_test_coverage_in_readme()
            break
        if line == "# Project Title\n":
            add_test_coverage_to_readme()
            break
    file_input.close()

add_or_update()
