Feature: User management
  Scenario: Register an user
    When I send a post request to "/user" with body:
    """
    {
      "data": {
        "id": "00000000-0000-0000-0000-000000000001",
        "email": "new@user.com"
      }
    }
    """
    Then the response status should be 201 with body:
    """
    {
      "id": "00000000-0000-0000-0000-000000000001",
      "email": "new@user.com"
      }
    }
    """

  Scenario: Update user email
    Given Users with the following data exists:
      | id                                   | email         |
      | 00000000-0000-0000-0000-000000000002 | old@email.com |
    When I send a post request to "/user/00000000-0000-0000-0000-000000000002" with body:
    """
    {
      "data": {
        "id": "00000000-0000-0000-0000-000000000002",
        "email": "new@email.com"
      }
    }
    """
    Then the response status should be 200 with body:
    """
    {
      "id": "00000000-0000-0000-0000-000000000002",
      "email": "new@email.com"
      }
    }
    """
