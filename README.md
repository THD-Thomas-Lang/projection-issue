# projection-issue
## included components
* spring data jpa (from spring boot 2.1.1)
* hibernate (from spring boot 2.1.1)
* postgres (used from testcontainers)

## issue
i want to do the following native query to get the latest rental cart return date:  
`select r.returndate as returnDateNative from rental r where r.returndate >= ? order by r.returndate asc limit 1`

i use a projection interface for this with two different properties:
* `LocalDate`
* `String`

if i want to use the `LocalDate` property it gives the exception `java.lang.IllegalArgumentException: Projection type must be an interface!` 
if i use the string property everything is fine.

maybe this is a hibernate issue?
