import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import logo from "../../../static/images/logolarge.png"
import user from './userinstance.json'
import List from "../Search/List"

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.profile
})

export const Profile = (props) => {
	const [view, setView] = useState('profile')


	let activePanel = 
		<>
			<div><label className="profiletext">{props.lang.name}: {user.firstname}</label></div>
			<div><label className="profiletext">{props.lang.surname}: {user.lastname}</label></div>
			<div><label className="profiletext">{props.lang.noactive}: {user.noactive}</label></div>
			<div><label className="profiletext">{props.lang.nowaiting}: {user.nowaiting}</label></div>
			<div><label className="profiletext">{props.lang.specialization}: {user.specialization}</label></div>
			<div><label className="profiletext">{props.lang.creation}: {user.creation_date}</label></div>
			<div><label className="profiletext">{props.lang.avgrade}: </label></div>
			<div class="star-ratings-css-profile">
				<div class="star-ratings-css-top" style={{width: user.avgrade/5*100 + '%'}}><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
				<div class="star-ratings-css-bottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
			</div>
			<div><label className="profiletext">{props.lang.description}: {user.description}</label></div>
		</>
	if(view === 'dishlist') {
		activePanel = 
			<>
				{user.dishlist.map( dish => (<List
					id={dish.id}
					picture={dish.picture}
					title={dish.title} 
					wystawiajacy={dish.cook}
					data={dish.date}
					lokalizacja={dish.location}
					ocena={dish.grade}
					opis={dish.description}
				></List>))}
			</>
	}
	else if(view === 'settings') {
		activePanel = 
			<>
				<label>settings prototype</label>
			</>
	}

	return (
		<div className="profilemain">
			<div className='content-box col25prof'>
				<img className="logolarge" alt="Logo" src={logo} />
				<button className="btn-blue-small" onClick={() => setView('profile')}>{props.lang.profile}</button>
				<button className="btn-blue-small" onClick={() => setView('dishlist')}>{props.lang.dishlist}</button>
				<button className="btn-blue-small" onClick={() => setView('settings')}>{props.lang.settings}</button>
			</div>			
			<div className='content-box col75'>
				{activePanel}	
			</div>
    	</div>
	)
}


export default connect(mapStateToProps)(Profile)