## Objective
Create a reusable CurrencyListFragment
Also create a DemoActivity to showcase the CurrencyListFragment

## Requirement
- CurrencyListFragment should receive an array list of CurrencyInfo to create the ui.
- DemoActivity should provide 1 dataset, Currency List A of CurrencyInfo to
CurrencyListFragment and the dataset should be queried from local db
- DemoActivity should provide 2 buttons to do the demo.
- First button to load the data and display
- Second button for sorting currency list
- All the IO operations MUST NOT be in UI Thread.
- Please show how to handle multi threading operation, and deal with concurrency
issue when do sorting (like fast double clicking of sorting button)
- Search functionality is not required
- Unit test is welcome
