import React from 'react'
//import { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'

export const Home = props => {
  return (
    <div>
      <div className="main">
        <div className="row">
          <div className="section">
            <Link to="search">
              <div className="img-container">
                <div className="img-main-section">
                  <img
                    src={`${process.env.PUBLIC_URL}/static/images/want-eat.jpeg`}
                    className="img-main-section"
                    alt=""
                  />
                </div>

                <div className="text-block">
                  <div>{props.lang.eat}</div>
                </div>
              </div>
            </Link>

            <div className="separator separator-horizontal" />

            <Link to="/search">
              <div className="img-container">
                <div className="img-main-section">
                  <img
                    src={`${process.env.PUBLIC_URL}/static/images/want-cook.jpeg`}
                    className="img-main-section"
                    alt=""
                  />
                </div>

                <div className="text-block">
                  <div>{props.lang.cook}</div>
                </div>
              </div>
            </Link>
          </div>

          <div className="separator separator-vertical" />

          <div className="section">
            <div className="text-section">
              <div style={{ width: '100%', textAlign: 'center' }}>
                <img
                  src={`${process.env.PUBLIC_URL}/static/images/food_Hero.png`}
                  alt=""
                />
              </div>
              <p className="quote">{props.lang.quote}</p>
              <p style={{ textAlign: 'center' }}>{props.lang.hero}</p>
              <p>{props.lang.what}</p>
              <p>{props.lang.society}</p>
              <p>{props.lang.makeOrder}</p>
              <p>{props.lang.makeDish}</p>
              <h3>{props.lang.try}</h3>
            </div>
            <div className="btn-container" style={{ margin: 'auto' }}>
              <Link to="/register">
                <button className="btn btn-register">
                  {props.lang.register}
                </button>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.home,
})

export default connect(mapStateToProps)(Home)
