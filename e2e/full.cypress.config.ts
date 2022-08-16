import { defineConfig } from "cypress";

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
    includeShadowDom: true,
    supportFile: `${__dirname}/cypress/support/e2e.ts`,
    specPattern: `${__dirname}/cypress/e2e/**/*.cy.{js,jsx,ts,tsx}`,
  },
});
