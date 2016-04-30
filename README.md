# device-magic

# just build & run.

## About

- Activities are responsible for the view data and interactions
- Presenters are called by views and delegate actions to the interactors / use cases
- Use cases and Interactors are responsible for execute requests from view layer and access repositories and do some business logic
- Repositories are responsible to get data from network / local databases.

- Repository layer has their own models
- Domain objects are visible to repository and should be returned in method calls. Repository entities should not return their models

