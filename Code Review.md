# Code Review Defect List

| Reviewer: Jorge Oviedo GH Repo: https://github.com/Jaoviedo98/jaoviedo-SER316|

ID#: 1
Location: Cart.java, Line number 37
Problem Description: The Method name Starts with an uppercase Letter. Violating the Coding Standards
Category: CG
Severity: LOW

ID#: 2
Location: Cart.java, Line number 81
Problem Description: The Identifier "twoLetterUSStateAbbreviation" is too long
Category: CS
Severity: LOW

ID#: 3
Location: Cart.java, Line number 10
Problem Description: cartStorage is a member variable of the class and should be private
Category: CG
Severity: BR

ID#: 4
Location: Cart.java, Line number 37
Problem Description: The Method is long and complex, could be broken down into multiple methods to calculate the savings. This issue makes it hard to read.
Category: CS
Severity: LOW

ID#: 5
Location: All classes line 1
Problem Description: There is no File Banner to define author and version.
Category: CG
Severity: MJ

ID#: 6
Location: Cart.java, Line number 118
Problem Description: Constructor of class is at the bottom of the class rather than right after variables as per coding standards.
Category: CG
Severity: MJ

ID#: 7
Location: Cart.java, Line number 54
Problem Description: The method reduces by 1 in cost if you buy 
Category: CG
Severity: LOW

ID#: 8
Location: Cart.java, Line number 67
Problem Description: There is no {} on the if statement
Category: CG
Severity: LOW

ID#: 9
Location: Cart.java, Line number 83
Problem Description: Switch Statement is described as a code Smell
Category: CS
Severity: MJ

ID#: 10
Location: Cart.java, Line number 84, 87, 90, 93
Problem Description: All literals (taxes) should be fixed constants
Category: CS
Severity: MJ

ID#: 11
Location: Cart.java, Line number 83
Problem Description: Switch Statement is described as a code Smell
Category: CS
Severity: MJ

ID#: 12
Location: Cart.java, Line number 67
Problem Description: dairy counter increased if frozen food is bought 
Category: FD
Severity: MJ

ID#: 13
Location: Cart.java, Line number 71
Problem Description: Cost after savings is increased instead of being decreased when alcohol and frozen food is bought.
Category: FD
Severity: BR


Category:        
**CS** – Code Smell defect. 
**CG** – Violation of a coding guideline. Provide the guideline number. 
**FD** – Functional defect. Code will not produce the expected result. 
**MD** – Miscellaneous defect, for all other defects.

Severity:       
**BR** - Blocker, must be fixed asap. 
**MJ** – Major, of high importance but not a Blocker 
**LOW** – Low.