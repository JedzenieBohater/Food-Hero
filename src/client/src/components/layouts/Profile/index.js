import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import logo from "../../../static/images/logolarge.png"

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.profile
})

export const Profile = (props) => {
	return (
		<div className="searchmain">
			<div className='content-box col25prof'>
				<img className="logolarge" alt="Logo" src={logo} />
				<button className="btn-blue-small">{props.lang.profile}</button>
				<button className="btn-blue-small">{props.lang.dishlist}</button>
				<button className="btn-blue-small">{props.lang.settings}</button>
			</div>			
			<div className='content-box col75'>
				<div><label className="profiletext">{props.lang.name}: Andrzej</label></div>
				<div><label className="profiletext">{props.lang.surname}: Sapkowski</label></div>
				<div><label className="profiletext">{props.lang.noactive}: 2</label></div>
				<div><label className="profiletext">{props.lang.nowaiting}: 0</label></div>
				<div><label className="profiletext">{props.lang.avgrade}: </label></div>
				<div class="star-ratings-css-profile">
					<div class="star-ratings-css-top" style={{width: 4/5*100 + '%'}}><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
					<div class="star-ratings-css-bottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
				</div>
				<div><label className="profiletext">{props.lang.description}: Numero uno on American Amazon</label></div>
			</div>
    	</div>
	)
}


export default connect(mapStateToProps)(Profile)