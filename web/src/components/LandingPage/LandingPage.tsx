import React, { FC } from "react";

interface LandingPageProps {}

const LandingPage: FC<LandingPageProps> = () => (
  <div className="text-center">
    <p>
      <a href="https://mrgreener.tech">
        <img height="80px" src="https://mrgreener.tech/logo512.png" />
      </a>
    </p>
    <h1 style={{ display: "inline" }}>MrGreener</h1>

    <p>Loyalty program for green initiatives</p>

    <h3 id="what-is-it">What is It</h3>
    <p>
      MrGreener aims to encourage people to buy more environmentally friendly
      goods and commit actions to improve the ecological situation.
    </p>
    <p>
      Our project helps different companies to cooperate. Some companies are
      willing to give some benefits to people who care about the environment.
      However, there are a lot of unknown organizations which are trying to save
      our planet. Our team would like to help people find such organizations and
      contribute to sustainability. In addition, MrGreener motivates people via
      virtual currency -- &quot;Green Points&quot; plus a profile page that
      people can share on social media.
    </p>
    <h3 id="how-does-it-work">How does it work</h3>
    <p>
      Organizations can add promotions such as &quot;Get 100 points for every 20
      euros on the bill&quot; or &quot;Get 5 points for every recycled
      bottle&quot;. Other organizations can add rewards such as &quot;Get 10
      euro gift card for 100 points&quot;. After contributing enough, the user
      can redeem accumulated points and receive benefits. We believe that
      organizations will be pleased to participate in our project because such
      motivation will involve many more people in fighting against environmental
      problems.
    </p>
  </div>
);

export default LandingPage;
