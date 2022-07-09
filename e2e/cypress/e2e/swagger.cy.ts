describe('Favorite Lyrics E2E Tests', () => {
  it('shows swagger', () => {
    cy.visit('http://localhost:8080/swagger-ui/index.html');
    cy.get('h2').contains('OpenAPI definition').should('not.be.null');
    cy.wait(1000);
  });
})