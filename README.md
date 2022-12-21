<h1 align="center">
  <a href="background"><img src="https://github.com/trongtuyen96/automationTestExample/blob/master/Background_with_title.PNG" alt="background"></a>
  <a href="cover"><img src="https://github.com/trongtuyen96/testng-allure-extent-framework/blob/22b017133819447cdbd6a18e5ec0be0730e2e82c/cover.png" alt="cover" width="734px"></a>
</h1>

<h3 align="center" style="bold">A series of instruction about setting up selenium grid, docker and zalenium with TestNG framework.</h3>

# Table of Contents
- [Getting Started](getting-started)
- [Set up Selenium Grid](#set-up-selenium-grid)
- [Set up Selenium Grid with standalone Docker image](#set-up-selenium-grid-with-standalone-docker-image)
- [Set up Selenium Grid with Docker compose](#set-up-selenium-grid-with-docker-compose)
- [Set up Selenium Grid with Zalenium](#set-up-selenium-grid-with-zalenium)
- [Author](#author)
- [License](#license)

# Getting Started
## Selenium Grid
Selenium Grid allows the execution of WebDriver scripts on remote machines by routing commands sent by the client to remote browser instances.

Grid aims to:
- Provide an easy way to run tests in parallel on multiple machines
  - Allow testing on different browser versions
  - Enable cross-platform testing

## Selenium Grid with Docker
Docker is an open-source containerization platform that makes it easy to create, deploy, and run applications in a secure manner using containers. Docker provides virtualization at the Operating System (OS) level. All the software parts in Docker are organized in Containers.

When it comes to Selenium automation testing, it is important that a test run in one execution environment does not hinder the execution of tests run in another test environment (s). Hence, automation tests should be run in isolation, and Docker helps in realizing this ‘essential’ requirement.

## Zalenium

# Set up Selenium Grid
## Prerequisites
- Java 11 or higher
- Browser(s) installed
- Download new Selenium Server jar: [latest release](https://github.com/SeleniumHQ/selenium/releases)

## Standalone mode
Standalone combines all Grid components seamlessly into one. Running a Grid in Standalone mode gives you a fully functional Grid with a single command, within a single process. Standalone can only run on a single machine.
- Start the Grid:
  ````bash
  java -jar selenium-server-<version>.jar standalone
  ````
- The server will start automatically at http://localhost:4444
- Point RemoteWebDriver in your test to http://localhost:4444
  ````bash
  ChromeOptions chromeOptions = new ChromeOptions();
  driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
  ````
- Run your test 

## Hub and Node mode
Hub and Node is the most used role because it allows to:
- Combine different machines in a single Grid
  - Machines with different operating systems and/or browser versions, for example
- Have a single entry point to run WebDriver tests in different environments
- Scaling capacity up or down without tearing down the Grid

### Hub
To start a hub:
  ````bash
  java -jar selenium-server-<version>.jar hub
  ````
By default, the server will listen for RemoteWebDriver requests on http://localhost:4444

### Node
The command below assumes the Node is running on the same machine where the Hub is running:
  ````bash
  java -jar selenium-server-<version>.jar node
  ````

To set up more nodes on the same machine, we can specify different ports for them:
  ````bash
  // Node 1
  java -jar selenium-server-<version>.jar node --port 5555
  
  // Node 2
  java -jar selenium-server-<version>.jar node --port 6666
  ````

### Hub and Node on different machines
Hub and Nodes talk to each other via HTTP and the Event Bus (the Event Bus lives inside the Hub). A Node sends a message to the Hub via the Event Bus to start the registration process. When the Hub receives the message, reaches out to the Node via HTTP to confirm its existence.

After starting the Hub with default ports, the --hub flag can be used to register the Node
  ````bash
  java -jar selenium-server-<version>.jar node --hub http://<hub-ip>:4444
  ````

When the Hub is not using the default ports, the --publish-events and --subscribe-events flags are needed.
For example, if the Hub uses ports 8886, 8887, and 8888
  ````bash
  java -jar selenium-server-<version>.jar hub --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887 --port 8888
  ````

The Node needs to use those ports to register successfully
  ````bash
  java -jar selenium-server-<version>.jar node --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887
  ````

## Distributed mode
When using a Distributed Grid, each component is started separately, and ideally on different machines.
So 6 of components below need to be started:
1. Event Bus
2. New Session Queue
3. Session Map
4. Distributor
5. Router
6. Nodes
The detailed instructions will be added later.

## Grid size suggestions
- Small: Standalone or Hub/Node with 5 or less Nodes.
- Middle: Hub/Node between 6 and 60 Nodes.
- Large: Hub/Node between 60 and 100 Nodes. Distributed with over 100 Nodes.

# Set up Selenium Grid with standalone Docker image


# Author
<h4 align="center">
	Tuyen Nguyen - Senior QA Automation Engineer
	</h4>
	<h5 align="center">
	<a href="trongtuyen96@gmail.com">trongtuyen96@gmail.com</a>
	</h5>
<p align="center">
	 <a alt="Github" href="https://github.com/trongtuyen96">
    <img src="https://user-images.githubusercontent.com/25218255/47360756-794c1f00-d6fa-11e8-86fa-7b1c2e4dda92.png" width="50">
  </a>
		 <a alt="LinkedIn" href="https://www.linkedin.com/in/tuyennguyen96/">
    <img src="https://user-images.githubusercontent.com/25218255/47360366-8583ac80-d6f9-11e8-8871-219802a9a162.png" width="50">
  </a>
		 <a alt="Facebook" href="https://www.facebook.com/ntrongtuyen96">
    <img src="https://user-images.githubusercontent.com/25218255/47360363-84eb1600-d6f9-11e8-8029-818481536200.png" width="50">
  </a>
</p>

# License
~~~~
Copyright 2022 Tuyen Nguyen

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
~~~~