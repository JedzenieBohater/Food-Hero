import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.home
})

export const Home = (props) => {
  return (
    <div>
      <div className="main">
        <div className="row">
          <div className="section">
            <div className="img-container">
              <a className="img-main-section">
                <img src="static/images/want-eat.jpeg" className="img-main-section" />
              </a>
              <a className="text-block">
                <div>{props.lang.eat}</div>
              </a>
            </div>
            <div className="separator separator-horizontal"></div>
            <div className="img-container">
              <a className="img-main-section">
                <img src="static/images/want-cook.jpeg" className="img-main-section" />
              </a>
              <a className="text-block">
                <div>{props.lang.cook}</div>
              </a>
            </div>
          </div>
          <div className="separator separator-vertical"></div>
          <div className="section">
            <div className="text-section">
              <div style={{ width: "100%", textAlign: "center" }}>
                <img src="static/images/food_Hero.png" />
              </div>
              <p className="quote">{props.lang.quote}</p>
              <p style={{ textAlign: "center" }}>{props.lang.hero}</p>
              <p>{props.lang.what}</p>
              <p>{props.lang.society}</p>
              <p>{props.lang.makeOrder}</p>
              <p>{props.lang.makeDish}</p>
              <h3>{props.lang.try}</h3>
            </div>
            <div className="btn-container" style={{ margin: "auto" }}>
              <Link to="/register">
                <button className="btn btn-register">{props.lang.register}</button>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}


export default connect(mapStateToProps)(Home)