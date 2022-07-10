describe('Favorite Lyrics E2E Tests', () => {
  it('shows swagger', () => {
    let host = Cypress.env('host') ?  Cypress.env('host') : 'localhost';
    cy.visit(`http://${host}:8080/swagger-ui/index.html`);
    cy.get('h2').contains('OpenAPI definition').should('not.be.null');
    cy.wait(1000);

    cy.get('div[class="servers"] > label > select > option').should('have.value', 'http://localhost:8080')
  });
})