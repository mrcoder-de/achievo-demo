# Notes about this Template

This template is a maven project with a predefined directory structure and
configuration files.

## How to extend

Once you've copied this template, take the following steps to make it your own:

- in settings.gradle
  - update the `rootProject.name`
- in build.gradle
  - update the `group`
- in `src/main/java`
  - remove `com.example.template.actions.TemplateAction`
  - rename `com.example.template.TemplateApplication`
  - update the package `com.some.corp.template` name to match the artifact and group ids
- in `src/main/resources`
  - in `application.yml` update `spring.application.name`
- in `src/test/java`
  - remove `com.example.template.steps.TemplateSteps`
  - rename `com.example.template.TemplateApplicationTests`
- in `src/test/resources`
  - remove `features/template.feature`
- update this README with documentation for your project

## Docker Compose

## Test Configuration

All test configuration can be found in the application-test.yml file.

When running test in maven, the "test" spring profile will be used automatically.