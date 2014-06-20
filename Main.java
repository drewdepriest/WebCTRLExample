/*
 * Copyright (c) 2014 Automated Logic Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.alcchi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controlj.green.addonsupport.access.ActionExecutionException;
import com.controlj.green.addonsupport.access.DirectAccess;
import com.controlj.green.addonsupport.access.FieldAccessFactory;
import com.controlj.green.addonsupport.access.Location;
import com.controlj.green.addonsupport.access.ReadAction;
import com.controlj.green.addonsupport.access.SystemAccess;
import com.controlj.green.addonsupport.access.SystemConnection;
import com.controlj.green.addonsupport.access.SystemException;
import com.controlj.green.addonsupport.access.SystemTree;
import com.controlj.green.addonsupport.access.aspect.PresentValue;
import com.controlj.green.addonsupport.access.value.FloatValue;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Main() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	// get connection to WebCTRL
	final SystemConnection connection = DirectAccess.getDirectAccess().getRootSystemConnection();
	
	// hard-coded GQL path to the point we want to read
	final String gqlPath = "#oat_1/enthalpy";
	
	// declare float value to hold the point value from WebCTRL
	float wcValue;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// declare printwriter to write to browser
		final PrintWriter pw = response.getWriter();
		
		// declare our string to print
		String str = "Enthalpy value: ";
		
		// write the value to the browser response object
		pw.write(str);
		
		// use the system connection to retrieve each GQL's present value
		try {
    		
			connection.runReadAction( FieldAccessFactory.newFieldAccess(), new ReadAction()
			{
			   public void execute(SystemAccess access) throws Exception
			   {
			       // tell WebCTRL where to look for this point
				   Location loc = access.getTree(SystemTree.Geographic).getRoot().getDescendant(gqlPath);
				   
				   // get the present value & cast it as a float
				   PresentValue pv = (PresentValue)loc.getAspect(PresentValue.class);
				   FloatValue valueObj = (FloatValue) pv.getValue();
				   wcValue = (float) valueObj.getValue();
				   
			   }
			} );
			
		} catch (ActionExecutionException e) {
			//System.out.println("Action Execution Exception caught while trying to read from WC.");
			e.printStackTrace();
			
		} catch (SystemException e) {
			//System.out.println("System Exception caught while trying to read from WC.");
			e.printStackTrace();
			
		}
		
		// write the value of the WebCTRL point to the browser response
		pw.println(wcValue);
				
		// close the printwriter
		pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
