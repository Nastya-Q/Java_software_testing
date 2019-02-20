Feature: Groups

  Scenario Outline: Group creation
    Given a set of groups
    When I create a new group with name <name>, header <header> and footer <footer>
    Then the new set of groups is equal to the old set with the added group

    Examples:
    | name      |  footer       |  header       |
    | test_name |  test_header  |  test_footer  |