describe('empty spec', () => {
    it('should have status Ok', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        cy.request(`http://${host}:8080/actuator/health`).its("body").its("status").should("eq", "UP");
    })

    it('go to homepage', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        cy.visit(`http://${host}:8080`);
    })

    it('should post Metallica - Until it sleeps (DELET test)', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        let hostUrl = `http://${host}:8080`;
        cy.request("POST", `${hostUrl}/lyrics`, {
            "lyrics": "And the pain still hates me",
            "participatingArtist": "Metallica"
        })
    });

    it('should delete all Metallica', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        let hostUrl = `http://${host}:8080`;
        cy.request("DELETE", `${hostUrl}/lyrics`, {
            "lyrics": "And the pain still hates me",
            "participatingArtist": "Metallica"
        })
        cy.visit(`http://${host}:8080`);
    });

    it('should post Metallica - Until it sleeps (POST test)', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        let hostUrl = `http://${host}:8080`;
        cy.request("POST", `${hostUrl}/lyrics`, {
            "lyrics": "And the pain still hates me",
            "participatingArtist": "Metallica"
        })
    });

    it('should show Metallica - Until it sleeps', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        cy.visit(`http://${host}:8080`);
        cy.get('div').contains("And the pain still hates me").should("not.be.null");
    })


    it('should put Metallica - And nothing else matters', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        let hostUrl = `http://${host}:8080`;
        cy.request("PUT", `${hostUrl}/lyrics`, {
            "lyrics": "And nothing else matters",
            "participatingArtist": "Metallica"
        })
    });

    it('should show to Metallica - And nothing else matters', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        cy.visit(`http://${host}:8080`);
        cy.get('div').contains("And the pain still hates me").should("not.be.null");
    })

    it('should delete all Metallica', () => {
        let host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
        let hostUrl = `http://${host}:8080`;
        cy.request("DELETE", `${hostUrl}/lyrics`, {
            "lyrics": "And the pain still hates me",
            "participatingArtist": "Metallica"
        })
        cy.visit(`http://${host}:8080`);
    });
})