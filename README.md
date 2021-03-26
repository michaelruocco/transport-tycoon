# Transport Tycoon

[![Build](https://github.com/michaelruocco/transport-tycoon/workflows/pipeline/badge.svg)](https://github.com/michaelruocco/transport-tycoon/actions)
[![codecov](https://codecov.io/gh/michaelruocco/transport-tycoon/branch/master/graph/badge.svg?token=FWDNP534O7)](https://codecov.io/gh/michaelruocco/transport-tycoon)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/272889cf707b4dcb90bf451392530794)](https://www.codacy.com/gh/michaelruocco/transport-tycoon/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/transport-tycoon&amp;utm_campaign=Badge_Grade)
[![BCH compliance](https://bettercodehub.com/edge/badge/michaelruocco/transport-tycoon?branch=master)](https://bettercodehub.com/)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_transport-tycoon&metric=alert_status)](https://sonarcloud.io/dashboard?id=michaelruocco_transport-tycoon)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_transport-tycoon&metric=sqale_index)](https://sonarcloud.io/dashboard?id=michaelruocco_transport-tycoon)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_transport-tycoon&metric=coverage)](https://sonarcloud.io/dashboard?id=michaelruocco_transport-tycoon)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_transport-tycoon&metric=ncloc)](https://sonarcloud.io/dashboard?id=michaelruocco_transport-tycoon)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Overview

This repo contains my attempt at these
[https://github.com/Softwarepark/exercises/blob/master/transport-tycoon.md](transport tycoon exercises).

## Running the tests

To run the tests you can use the following command:

```gradle
./gradlew build integrationTest
```


## Running the application

To run the application you can use the following command:

```gradle
./gradlew run
```

## Useful Commands

```gradle
// cleans build directories
// prints currentVersion
// formats code
// builds code
// runs tests
// checks for gradle issues
// checks dependency versions
./gradlew clean currentVersion dependencyUpdates lintGradle spotlessApply build integrationTest
```