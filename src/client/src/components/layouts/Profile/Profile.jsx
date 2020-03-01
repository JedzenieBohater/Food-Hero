import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import logo from "../../../static/images/logolarge.png"
import user from './userinstance.json'
import List from "../Search/List"
import { getAccountData } from "../../../utils/user"

const mapStateToProps = ({ sessionReducer, languageReducer }) => ({
	lang: languageReducer.profile,
	session: sessionReducer
})

export const Profile = (props) => {
	const [view, setView] = useState('profile')
	const [email, setEmail] = useState(user.email)
	const [firstname, setFirstname] = useState(user.firstname)
	const [lastname, setLastname] = useState(user.lastname)
	const [description, setDescription] = useState(user.description)
	const [specialization, setSpecialization] = useState(user.specialization)
	const [errorMessage, setErrorMessage] = useState(null)
	const [creationDate, setCreationDate] = useState(user.creation_date)
	const [grade, setGrade] = useState(user.grade)

	useEffect(() => {
		(async() => {
			let account = await getAccountData(props.session)
			console.log(account)
			setFirstname(account.firstname)
			setLastname(account.lastname)
			setDescription(account.description)
			setSpecialization(account.specialization)
			setCreationDate(String(account.creation_date).slice(0,10))
			setGrade(account.grade)
			
		})()
		
	}, [])

	const handleSubmit = async event => {
		event.preventDefault()
		if (email.length < 5 || email.length > 50) {
			setErrorMessage(props.lang.emailincorrect)
		}
		if (firstname.length < 1 || firstname.length > 30 || !/^[a-zA-Z]+$/.test(firstname)) {
			setErrorMessage(props.lang.firstnameincorrect)
		}
		else if (lastname.length < 1 || lastname.length > 30 || !/^[a-zA-Z]+$/.test(lastname)) {
			setErrorMessage(props.lang.lastnameincorrect)
		}
		else if (description.length > 255) {
			setErrorMessage(props.lang.descincorrect)
		}
		else if (specialization.length < 1 || specialization.length > 30 || !/^[a-zA-Z\s]+$/.test(specialization)) {
			setErrorMessage(props.lang.specializationincorrect)
		}
		else {
			setErrorMessage(null)
		}
		//zaslepka
	}
	let error = null
	if (errorMessage != null) {
		error = 
			<label className="profileerror">{errorMessage}</label>
	}
	let activePanel =
		<table className="tableform">
			<tbody>
				<tr>
					<td>
						<label htmlFor='name'>{props.lang.name}: </label>
					</td>
					<td>
						<label >{firstname}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.surname}: </label>
					</td>
					<td>
						<label >{lastname}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.noactive}: </label>
					</td>
					<td>
						<label >{user.noactive}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.nowaiting}:</label>
					</td>
					<td>
						<label >{user.nowaiting}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.specialization}: </label>
					</td>
					<td>
						<label >{specialization}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.creation}: </label>
					</td>
					<td>
						<label >{creationDate}</label>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.avgrade}:</label>
					</td>
					<td>
						<div className="star-ratings-css-profile">
							<div className="star-ratings-css-top" style={{ width: grade / 5 * 100 + '%' }}><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
							<div className="star-ratings-css-bottom"><span>★</span><span>★</span><span>★</span><span>★</span><span>★</span></div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label >{props.lang.description}:</label>
					</td>
					<td>
						<label >&nbsp;&nbsp;&nbsp;{description}</label>
					</td>
				</tr>
			</tbody>
		</table>
	if (view === 'dishlist') {
		activePanel =
			<>
				{user.dishlist.map(dish => (<List
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
	else if (view === 'settings') {
		activePanel =
			<form>
				<table className="tableform">
					<tbody>
						<tr>
							<td>
								<label htmlFor="email">{props.lang.email}:</label>
							</td>
							<td>
								<input onChange={e => setEmail(e.target.value)} type="email" id="email" placeholder={props.lang.email.toLowerCase()} value={email} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="firstname">{props.lang.name}:</label>
							</td>
							<td>
								<input onChange={e => setFirstname(e.target.value)} type="text" id="firstname" placeholder={props.lang.name.toLowerCase()} value={firstname} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="lastname">{props.lang.surname}:</label>
							</td>
							<td>
								<input onChange={e => setLastname(e.target.value)} type="text" id="lastname" placeholder={props.lang.surname.toLowerCase()} value={lastname} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="specialization">{props.lang.specialization}:</label>
							</td>
							<td>
								<input onChange={e => setSpecialization(e.target.value)} type="text" id="specialization" placeholder={props.lang.specialization.toLowerCase()} value={specialization} />
							</td>
						</tr>
						<tr>
							<td>
								<label htmlFor="description">{props.lang.description}:</label>
							</td>
							<td>
								<input onChange={e => setDescription(e.target.value)} type="text" id="description" placeholder={props.lang.description.toLowerCase()} value={description} />
							</td>
						</tr>
						<tr>
							<td colSpan="2">
								<button
									className="btn-blue"
									onClick={handleSubmit}
									disabled={true ? '' : 'disabled'}
								>
									{props.lang.submit}
								</button>
							</td>
						</tr>
						
					</tbody>
				</table>
				{error}
			</form>
	}

	return (
		<div className="profilemain">
			<div className='content-box col25prof'>
				<img className="logolarge" alt="Logo" src={logo} />
				<button className="btn-blue-small" onClick={() => setView('profile')}>{props.lang.profile}</button>
				<button className="btn-blue-small" onClick={() => setView('dishlist')}>{props.lang.dishlist}</button>
				<button className="btn-blue-small" onClick={() => setView('settings')}>{props.lang.settings}</button>
			</div>
			<div className='content-box col75prof'>
				{activePanel}
			</div>
		</div>
	)
}


export default connect(mapStateToProps)(Profile)