JP Morgan Web Automation.

## Architecture

This is a serenity framework which can be studied at [Serenity Documentation](https://serenity-bdd.info/docs/serenity/)

This framework promotes the principles of `behaviour-driven development` to write the test scenarios.

The development of the test automation framework uses the outlined technologies and `Java` as its underlying programming
language.

### Getting Started

Outlined before are the artefacts used to develop the automation framework and prerequisites to get started with setting
up your workstation to facilitate development locally:

#### Homebrew

[Brew](https://brew.sh/) is a `Mac OSX` or `Linux` missing package manager, below is how to install:

```bash
    /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

#### Node.js

[Node.js](https://nodejs.org/en/) is a `JavaScript` runtime built on Chrome's V8 JavaScript engine. Node.js can be
installed on `Mac OSX` as shown below using Homebrew:

```bash
    brew install node
```

For other OS platform, kindly
see [Installing Node.js via package manager](https://nodejs.org/en/download/package-manager/)

#### Serenity-BDD

[Serenity BDD](https://serenity-bdd.github.io/theserenitybook/latest/index.html) is a test automation library designed
to make writing automated acceptance tests easier, and more fun.

#### Gradle

[Gradle](https://docs.gradle.org/) is a build automation tool for multi-language software development, helping teams
build, automate and deliver better software, faster.

Installing Gradle using the command line:

```bash
    brew install gradle
```

Kindly refer to [Gradle Installation Guide](https://gradle.org/install/) for further details on installing on `Linux`
and `Windows`.

### Under the hood

The architecture focuses on `three` key aspects of automation namely - `driver service`, `driver capabilities`,
and `browser`.

#### Driver Service

The [Driver Service Manager](src/test/java/com/chaseTest/automation/ui/qa/core/DriverServiceManager.java)
leverages `WebDriver Driver Service` class to instantiate a specified browser driver. The `Driver Service Manager`
handles the configuration of specified driver service, starting and stopping the service, and fetching the driver serve
URL.

#### Driver Capabilities

The [Driver Capabilities](src/test/java/com/chaseTest/automation/ui/qa/core/capabilities) encapsulate the configuration of
driver capabilities, enabling the flexibility to customize the behaviour of the driver.

#### Browser Manager

The [Browser Manager](src/test/java/com/chaseTest/automation/ui/qa/core/BrowserManager.java)
leverages [WebDriverDriverManager](https://github.com/bonigarcia/webdrivermanager) to autonomously manage the webdriver
browser path, therefore downloading the required browser remotely, and setting the
required `system environment variable` at runtime. This flexibility ensures no setup or management of the webdriver on
local machines or CI servers.


##### Test Executions

Executing regression tests using this framework focuses on various configurations as defined in
the [serenity.conf](src/test/resources/serenity.conf) file.

Running this project on Intellij by setting up the configuration with below values:

_E.g:_
**Run**
 ```bash
    clean autoLintGradle clearReports test aggregate reports
```
**VM options**
```bash
    -Dselenium.browser.name="Firefox" -Denvironment="qa" 
```

**To run on Terminal Command**
```bash
    ./gradlew clean autoLintGradle clearReports test aggregate reports -Dselenium.browser.name="Firefox" -Denvironment="qa"
```

**Reporting**

From the above command **aggregate** generates the [Serenity Reports](target/site/serenity/index.html) 
with fancy styling of feature/test-case/steps coverage with screenshots/video for individual step error. 
To view serenity reports in the project, navigate to index.html file in the path given.

**E.g:**

[Screenshot 2024-10-30 at 01.44.40.pdf](Screenshot%202024-10-30%20at%2001.44.40.pdf)

&copy; 2024 Web Test Application