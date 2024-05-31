import React from 'react';
import { FaMapMarkerAlt, FaPhone } from 'react-icons/fa'; // Importing icons
import './AboutUs.css'; // assuming you have a CSS file for styling

function AboutUs() {
  return (
    <section className="container">
      <div className="team">
        <h2>Our Team</h2>
        <div className="team-member center-member">
          <div className="member-box">
            <h3>Vishal Savle</h3>
            <p>Software Engineer</p>
            <p>Email: vksavle009@gmail.com</p>
            <p>Vishal is a talented software engineer with expertise in web development. He is dedicated to writing clean, efficient code and loves tackling complex challenges. With his strong problem-solving skills, he plays a crucial role in our development team.</p>
          </div>
        </div>
        <div className="team-pair">
          <div className="team-member">
            <div className="member-box">
              <h3>Kapil Patel</h3>
              <p>Software Engineer</p>
              <p>Email: tech.kapilpatel48@gmail.com</p>
              <p>Kapil is talented developer possesses a deep understanding of programming languages, frameworks, tools, and technologies relevant to their field. They are adept at both frontend and backend development, and they continuously strive to stay updated with the latest advancements in the industry. </p>
            </div>
          </div>
          <div className="team-member">
            <div className="member-box">
              <h3>Priyansh Khare</h3>
              <p>Software Engineer</p>
              <p>Email: vishukhare47@gmail.com</p>
              <p>Priyansh  is not just someone who can write code, but someone who can leverage their technical skills, creativity, and problem-solving abilities to build elegant solutions that make a positive impact in the digital world</p>
            </div>
          </div>
        </div>
      </div>
      <div className="company-description">
        <h2>About Our Company</h2>
        <p>Railworld India is an globally IT/Software developing company who has best IT solutions provider specializing in services for coding in the industry of IT/Software Development as well as end users.</p>
       <p>Software development is a rapidly-changing field and software developers need to stay up-to-date on the latest advancements regarding the tools, frameworks, programming languages, and apps to achieve success. You can master the latest coding tools, strategies, and techniques through Simplilearn’s acclaimed Software Development courses.
At present, we are all surrounded by software. The number of individuals using computers in 2008 throughout the world crosses 1 billion. In 2019 there are 5.11 billion mobile users in the world. We know that all of these devices are functioning by an operating system that is a piece of software with lots of integrated functions. This is the function of the software. However, individuals and businesses throughout the world use software for many reasons and gaining lots of benefits. It is used in transportation, electrical grids, nuclear plants, and other functions that offer the basics of life.</p>
      <p></p>
        <div className="company-address">
          <h3>Contact Us</h3>
          <p><FaMapMarkerAlt /> 1st Floor, Office No.102, Swastik Urbane Building, Plot No. 8, PU 4, Scheme 54, in Front of CHAI KAAPI, Vijay Nagar, Indore - 452010 (M.P.)</p>
          <p><FaPhone /> (+91) 9289921216</p>
        </div>
      </div>
    </section>
  );
}

export default AboutUs;
