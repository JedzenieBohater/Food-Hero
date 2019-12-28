import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'


const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.home
})

export const Home = (props) => {
  return (
    <div className="middle">
      <Link to="/register">Dołącz do nas :)</Link>
    </div>
  )
}


export default connect(mapStateToProps)(Home)