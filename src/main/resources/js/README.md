# How to populate the DB

To populate the DB you can generate a set of `SQL` scripts by running a `node.js` script. This requires just several steps:
1. In the current directory `src/main/resources` run a terminal command `$ npm i`. As a prerequisite `node.js` needs to be installed globally on your machine.
2. Run `$ node generatePopulationScripts.js`.
3. Check that the `SQL` scripts have been created for you under the `src/main/resources/database` path.
4. Use them to populate the DB by the means of `Liquibase`.
